module Template where

import Prelude

import Common.CommonStrings (commonString)
import Control.Monad.Trans.Class (lift)
import Control.MonadZero (guard)
import Data.Argonaut (class DecodeJson, decodeJson, (.?), (.??))
import Data.Array (catMaybes, concat, intercalate)
import Data.Either (Either(..), either)
import Data.Maybe (Maybe(Just, Nothing), fromJust, fromMaybe, isJust, isNothing, maybe)
import Data.Nullable (Nullable, toMaybe, toNullable)
import Data.String (joinWith)
import Dispatcher (affAction)
import Dispatcher.React (getProps, getState, modifyState, renderer)
import EQUELLA.Environment (baseUrl, prepLangStrings)
import Effect (Effect)
import Effect.Class (liftEffect)
import Effect.Class.Console (log)
import Effect.Uncurried (mkEffectFn1, runEffectFn1)
import Foreign.Object as M
import MaterialUI.AppBar (appBar)
import MaterialUI.Badge (badge, badgeContent)
import MaterialUI.Button (button)
import MaterialUI.Color (inherit, secondary)
import MaterialUI.Color as C
import MaterialUI.CssBaseline (cssBaseline_)
import MaterialUI.Dialog (dialog)
import MaterialUI.DialogActions (dialogActions_)
import MaterialUI.DialogContent (dialogContent_)
import MaterialUI.DialogContentText (dialogContentText_)
import MaterialUI.DialogTitle (dialogTitle_)
import MaterialUI.Divider (divider)
import MaterialUI.Drawer (anchor, drawer, left, open, permanent, temporary)
import MaterialUI.Hidden (css, hidden, implementation, mdUp, smDown)
import MaterialUI.Icon (icon, icon_)
import MaterialUI.IconButton (iconButton)
import MaterialUI.List (list)
import MaterialUI.ListItem (button) as LI
import MaterialUI.ListItem (listItem)
import MaterialUI.ListItemIcon (listItemIcon_)
import MaterialUI.ListItemText (disableTypography, listItemText, primary)
import MaterialUI.Menu (anchorEl, menu)
import MaterialUI.MenuItem (menuItem)
import MaterialUI.Popover (anchorOrigin, transformOrigin)
import MaterialUI.Properties (className, classes_, color, component, mkProp, onClick, onClose, variant)
import MaterialUI.Radio (default)
import MaterialUI.Styles (MediaQuery, allQuery, cssList, mediaQuery, withStyles)
import MaterialUI.TextStyle (subheading)
import MaterialUI.TextStyle as TS
import MaterialUI.Toolbar (disableGutters, toolbar)
import MaterialUI.Tooltip (tooltip, title)
import MaterialUI.Typography (typography)
import MaterialUIPicker.DateFns (momentUtils)
import MaterialUIPicker.MuiPickersUtilsProvider (muiPickersUtilsProvider, utils)
import Network.HTTP.Affjax (get)
import Network.HTTP.Affjax.Response as Resp
import Partial.Unsafe (unsafePartial)
import React (Children, ReactClass, ReactElement, childrenToArray, createElement)
import React as R
import React.DOM (footer, text)
import React.DOM as D
import React.DOM.Props as DP
import ReactDOM (render)
import Routes (Route, forcePushRoute, matchRoute, pushRoute, routeHref, setPreventNav)
import SearchResults (SearchResultsMeta(SearchResultsMeta))
import Utils.UI (withCurrentTarget)
import Web.DOM.NonElementParentNode (getElementById)
import Web.Event.EventTarget (EventListener, addEventListener, removeEventListener)
import Web.HTML (HTMLElement, window)
import Web.HTML.Event.BeforeUnloadEvent.EventTypes (beforeunload)
import Web.HTML.HTMLDocument (toNonElementParentNode)
import Web.HTML.Window (document, toEventTarget)

newtype ExternalHref = ExternalHref String 
newtype MenuItem = MenuItem {route::Either ExternalHref String, title::String, systemIcon::Maybe String}

newtype MenuLinks = MenuLinks (Array (Array MenuItem))

data Command = Init | Updated {preventNavigation :: Nullable Boolean, title::String} | AttemptRoute Route | NavAway Boolean
  | ToggleMenu | UserMenuAnchor (Maybe HTMLElement)  | GoBack

type UserData = {
  id::String, 
  guest::Boolean, 
  autoLogin::Boolean, 
  prefsEditable::Boolean
}

type RenderData = {
  baseResources::String, 
  newUI::Boolean, 
  user::UserData
}

foreign import preventUnload :: EventListener

foreign import renderData :: RenderData

foreign import setTitle :: String -> Effect Unit

nullAny :: forall a. Nullable a
nullAny = toNullable Nothing

type TemplateProps = (
  title::String, 
  fixedViewPort :: Nullable Boolean, -- Fix the height of the main content, otherwise use min-height
  preventNavigation :: Nullable Boolean, -- Prevent navigation away from this page (e.g. Unsaved data) 
  titleExtra :: Nullable ReactElement, -- Extra part of the App bar (e.g. Search control)
  menuExtra :: Nullable (Array ReactElement), -- Extra menu options
  tabs :: Nullable ReactElement, -- Additional markup for displaying tabs which integrate with the App bar
  footer :: Nullable ReactElement, -- Markup to show at the bottom of the main area. E.g. save/cancel options
  backRoute :: Nullable Route, -- An optional Route for showing a back icon button
  menuMode :: String,
  fullscreenMode :: String,
  hideAppBar :: Boolean
)

type State = {
  mobileOpen::Boolean, 
  menuAnchor::Maybe HTMLElement, 
  tasks :: Maybe Int, 
  notifications :: Maybe Int, 
  attempt :: Maybe Route, 
  menuItems :: Array (Array MenuItem)
}

initialState :: State
initialState = {
  mobileOpen: false, menuAnchor: Nothing, tasks: Nothing, 
  notifications: Nothing, attempt: Nothing, menuItems: []
}

template :: String -> Array ReactElement -> ReactElement
template title = template' $ templateDefaults title

template' :: {|TemplateProps} -> Array ReactElement -> ReactElement
template' = createElement templateClass

templateDefaults ::  String ->  {|TemplateProps} 
templateDefaults title = {title,titleExtra:nullAny, fixedViewPort:nullAny,preventNavigation:nullAny, menuExtra:nullAny, 
  tabs:nullAny, backRoute: nullAny, footer: nullAny, menuMode:"", fullscreenMode:"", hideAppBar: false}

templateClass :: ReactClass {children::Children|TemplateProps}
templateClass = withStyles ourStyles $ R.component "Template" $ \this -> do
  let
    d = eval >>> affAction this
    boolNull = fromMaybe false <<< toMaybe

    strings = prepLangStrings rawStrings
    coreString = prepLangStrings coreStrings

    setUnloadListener :: Boolean -> Effect Unit
    setUnloadListener add = do 
      w <- window
      (if add then addEventListener else removeEventListener) beforeunload preventUnload false $ toEventTarget w 

    setPreventUnload add = do 
      liftEffect $ setPreventNav (mkEffectFn1 \r -> do 
        if add then affAction this $ eval (AttemptRoute r) else pure unit
        pure add
      )
      liftEffect $ setUnloadListener add

    render {state: state@{mobileOpen,menuAnchor,tasks,notifications,attempt}, props:props@{fixedViewPort:fvp, classes, 
                title:titleText,titleExtra,menuExtra,backRoute}} = muiPickersUtilsProvider [utils momentUtils] [
      D.div [DP.className classes.root] $ [
        cssBaseline_ [],
        layout props.fullscreenMode props.menuMode props.hideAppBar, 
        dialog [ open $ isJust attempt] [
          dialogTitle_ [ text strings.navaway.title], 
          dialogContent_ [
            dialogContentText_ [ text strings.navaway.content ]
          ], 
          dialogActions_ [
            button [onClick $ \_ -> d $ NavAway false, color C.secondary] [text commonString.action.cancel],
            button [onClick $ \_ -> d $ NavAway true, color C.primary] [text commonString.action.discard]
          ]
        ]
      ]
    ]
      where
      children = childrenToArray props.children
      tabsM = toMaybe props.tabs
      fixedViewPort = fromMaybe false $ toMaybe fvp 

      contentClass = if fixedViewPort then classes.contentFixedHeight else classes.contentMinHeight
      content = D.main [ DP.className $ joinWith " " $ [classes.content, contentClass] ] $  
        catMaybes [
          Just $ D.div [DP.className classes.toolbar] [],
          tabsM $> D.div [DP.className classes.tabs] [],
          Just $ D.div [DP.className classes.contentArea] children
        ]
      fullscreen = D.main' children
      layout "YES" _ _ = fullscreen
      layout "YES_WITH_TOOLBAR" _ _ = fullscreen 
      layout _ _ true = fullscreen
      layout _ _ _ = D.div [DP.className classes.appFrame] $ [
        topBar,
        hidden [ mdUp true ] [
          drawer [ variant temporary, anchor left, classes_ {paper: classes.drawerPaper},
                    open mobileOpen, onClose (\_ -> d ToggleMenu) ] menuContent ],
        hidden [ smDown true, implementation css ] [
          drawer [variant permanent, anchor left, open true, classes_ {paper: classes.drawerPaper} ] menuContent
        ],
        content
      ] <> catMaybes [
        toMaybe props.footer <#> \fc -> footer [DP.className classes.footer] [ 
          fc
        ]
      ]
      hasMenu = case props.menuMode of 
        "HIDDEN" -> false 
        _ -> true
      topBar = appBar [className $ classes.appBar] $ catMaybes [
        Just $ toolbar [disableGutters true] $ concat [
          guard hasMenu $> iconButton [
              color C.inherit, className classes.navIconHide, 
              onClick $ \_ -> d $ ToggleMenu] [ icon_ [D.text "menu" ] 
          ], [
            D.div [DP.className classes.titleArea] $ catMaybes [
              toMaybe backRoute $> iconButton [color C.inherit, onClick $ \_ -> d GoBack] [ icon_ [D.text "arrow_back" ] ],
              Just $ typography [variant TS.headline, color C.inherit, className classes.title] [ D.text titleText ], 
              toMaybe titleExtra
            ],
            userMenu 
          ]
        ], 
        tabsM
      ]
      topBarString = coreString.topbar.link

      userMenu = D.div [DP.className classes.userMenu ] $ (fromMaybe [] $ toMaybe menuExtra) <>
        (guard (not renderData.user.guest) *>
        [
          badgedLink "assignment" tasks "access/tasklist.do" topBarString.tasks , 
          badgedLink "notifications" notifications "access/notifications.do" topBarString.notifications,
          tooltip [title strings.menu.title] [ 
            iconButton [color inherit, mkProp "aria-label" strings.menu.title, 
              onClick $ withCurrentTarget $ d <<< UserMenuAnchor <<< Just] [
              icon_ [ D.text "account_circle"]
            ]
          ],
          menu [
              anchorEl $ toNullable menuAnchor,
              open $ isJust menuAnchor,
              onClose $ \_ -> d $ UserMenuAnchor Nothing,
              anchorOrigin $ { vertical: "top", horizontal: "right" },
              transformOrigin $ { vertical: "top", horizontal: "right" }
          ] $ catMaybes
            [ Just $ menuItem [component "a", mkProp "href" "logon.do?logout=true"] [D.text strings.menu.logout],
              guard renderData.user.prefsEditable $> menuItem [component "a", mkProp "href" "access/user.do"] [D.text strings.menu.prefs]
            ]
        ])
      badgedLink iconName count uri tip = 
        let iconOnly = icon_ [ D.text iconName ]
            buttonLink col content = iconButton [mkProp "href" uri, color col, mkProp "aria-label" tip ] [ content ]
        in tooltip [ title tip ] [ 
          case fromMaybe 0 count of
              0 -> buttonLink default iconOnly
              c -> buttonLink inherit $ badge [badgeContent c, color secondary] [iconOnly]
        ]
      menuContent = [D.div [DP.className classes.logo] [ D.img [ DP.role "presentation", DP.src logoSrc] ]] <>
                    intercalate [divider []] (group <$> state.menuItems)
        where
          logoSrc = renderData.baseResources <> "images/new-equella-logo.png"
          group items = [list [component "nav"] (navItem <$> items)]
          navItem (MenuItem {title,systemIcon,route}) = listItem (linkProps <> [LI.button true, component "a"])
            [
              listItemIcon_ [icon [ color C.inherit ] [ D.text $ fromMaybe "folder" $ systemIcon ] ],
              listItemText [disableTypography true, primary $ typography [variant subheading, component "div"] [text title]]
            ]
            where 
              linkProps = case route of 
                Right r | Just m <- routeHref <$> matchRoute r -> [mkProp "href" m.href, onClick $ runEffectFn1 m.onClick]
                Left (ExternalHref href) -> [mkProp "href" href]
                Right r -> [mkProp "href" $ show r]

    setWindowTitle title = liftEffect $ setTitle $ title <> coreString.windowtitlepostfix

    eval (GoBack) = do 
      {backRoute} <- getProps
      liftEffect $ maybe (pure unit) pushRoute $ toMaybe backRoute  
    eval (NavAway n) = do 
      {attempt} <- getState
      liftEffect $ guard n *> attempt # maybe (pure unit) forcePushRoute
      modifyState _{attempt = Nothing}
    eval (AttemptRoute r) = do 
      modifyState _{attempt = Just r}
    eval (Updated {preventNavigation:oldpn,title:oldtitle}) = do 
      {preventNavigation, title} <- getProps
      let isTrue = fromMaybe false <<< toMaybe
          newPN = isTrue preventNavigation
      if isTrue oldpn /= newPN then setPreventUnload newPN else pure unit
      if oldtitle /= title then setWindowTitle title else pure unit
      pure unit
    eval Init = do 
      {title,preventNavigation:pn} <- getProps
      setWindowTitle title
      if fromMaybe false $ toMaybe pn then setPreventUnload true else pure unit
      mr <- lift $ get Resp.json $ baseUrl <> "api/content/menu"
      either (lift <<< log) (\(MenuLinks ml) -> modifyState _ {menuItems = ml})  (decodeJson mr.response)
      r <- lift $ get Resp.json $ baseUrl <> "api/task"
      either (lift <<< log) (\(SearchResultsMeta {available}) -> modifyState _ {tasks = Just available})  (decodeJson r.response)
      r2 <- lift $ get Resp.json $ baseUrl <> "api/notification"
      either (lift <<< log) (\(SearchResultsMeta {available}) -> modifyState _ {notifications = Just available})  (decodeJson r2.response)

    eval ToggleMenu = modifyState \(s :: State) -> s {mobileOpen = not s.mobileOpen}
    eval (UserMenuAnchor el) = modifyState \(s :: State) -> s {menuAnchor = el}

  pure {
    render: renderer render this, 
    state:initialState, 
    componentDidMount: d Init, 
    componentDidUpdate: \{preventNavigation,title} _ _ -> d $ Updated { preventNavigation, title},
    componentWillUnmount: setUnloadListener false
  }
  where
    drawerWidth = 240
    tabHeight = 48 
    ourStyles theme = 
      let desktop :: forall a. {|a} -> MediaQuery
          desktop = mediaQuery $ theme.breakpoints.up "md"
          mobile :: forall a. {|a} -> MediaQuery
          mobile = mediaQuery $ theme.breakpoints.up "sm"
      in {
      root: {
        width: "100%",
        zIndex: 1
      },
      title: cssList [ 
        desktop {
          marginLeft: theme.spacing.unit * 4
        }, 
        allQuery {
          overflow: "hidden", 
          whiteSpace: "nowrap", 
          textOverflow: "ellipsis",
          marginLeft: theme.spacing.unit
        }
      ],
      appFrame: {
        position: "relative"
      },
      appBar: cssList [ 
        allQuery {
          position: "fixed",
          marginLeft: drawerWidth
        },
        desktop { 
          width: "calc(100% - " <> show drawerWidth <> "px)"
        }
      ],
      navIconHide: desktop { 
        display: "none" 
      },
      toolbar: theme.mixins.toolbar,
      drawerPaper: cssList [ 
        desktop {
          position: "fixed"
        },
        allQuery { 
          width: drawerWidth 
        }
      ],
      tabs: {
        height: tabHeight
      },
      contentMinHeight: { 
        minHeight: "100vh"
      },
      contentFixedHeight: {
        height: "100vh"
      },
      "@global": cssList [
        allQuery {
          a: {
            textDecoration: "none",
            color: theme.palette.primary.main
          }
        }
      ],
      content: cssList [
        allQuery {
          display: "flex",
          flexDirection: "column"
        },
        desktop {
          marginLeft: drawerWidth
        }
      ],
      logo: {
        textAlign: "center",
        marginTop: theme.spacing.unit * 2
      }, 
      titleArea: {
        flexGrow: 1,
        display: "flex", 
        alignItems: "center", 
        overflow: "hidden"
      }, 
      contentArea: {
        flexGrow: 1, 
        flexBasis: 0,
        minHeight: 0
      },
      userMenu: {
        flexShrink: 0
      }, 
      footer: cssList [
        allQuery {
          position: "fixed", 
          right: 0,
          bottom: 0, 
          zIndex: 1000,
          width: "100%"
        }, 
        desktop {
          width: "calc(100% - " <> show drawerWidth <> "px)"
        }
      ]
    }


renderReact :: String -> ReactElement -> Effect Unit
renderReact divId main = do
  void (elm' >>= render main)
  where

  elm' = do
    win <- window
    doc <- document win
    elm <- getElementById divId (toNonElementParentNode doc)
    pure $ unsafePartial (fromJust elm)


renderMain :: ReactElement -> Effect Unit
renderMain = renderReact "mainDiv"

rawStrings :: { prefix :: String
, strings :: { menu :: { title :: String
                       , logout :: String
                       , prefs :: String
                       }
             , navaway :: { title :: String
                          , content :: String
                          }
             }
}
rawStrings = {prefix: "template", 
  strings: {
    menu: {
      title: "My Account",
      logout:"Logout",
      prefs:"My preferences"
    }, 
    navaway: {
      title:  "You have unsaved changes", 
      content: "If you leave this page you will lose your changes."
    }
  }
}

coreStrings :: { prefix :: String
, strings :: { windowtitlepostfix :: String
             , topbar :: { link :: { notifications :: String
                                   , tasks :: String
                                   }
                         }
             }
}
coreStrings = {prefix: "com.equella.core",
  strings: {
    windowtitlepostfix: " | EQUELLA",
    topbar: { 
      link: {
        notifications: "Notifications",
        tasks: "Tasks"
      }
    }
  }
}

instance decodeMI :: DecodeJson MenuItem where 
  decodeJson v = do 
    o <- decodeJson v
    href <- o .?? "href"
    route <- o .?? "route" <#> case _ of 
      Just r -> Right r 
      _ | Just h <- href -> Left $ ExternalHref h
      _ -> Right "home.do"
    title <- o .? "title"
    systemIcon <- o .?? "systemIcon"
    pure $ MenuItem {title, route, systemIcon}

instance decodeML :: DecodeJson MenuLinks where 
  decodeJson v = do 
    o <- decodeJson v
    ml <- o .? "groups"
    pure $ MenuLinks ml 