import * as React from "react";
import {
  templateDefaults,
  templateError,
  TemplateUpdateProps,
  TemplateProps,
  FullscreenMode,
  MenuMode
} from "./Template";
import { Bridge } from "../api/bridge";
import { Location, LocationDescriptor } from "history";
import { ErrorResponse } from "../api/errors";
import ErrorPage from "./ErrorPage";
import ScreenOptions from "./ScreenOptions";

declare var bridge: Bridge;

interface LegacyPageProps extends TemplateUpdateProps {
  location: Location;
  refreshUser: () => void;
  redirect: (location: LocationDescriptor) => void;
  setPreventNavigation: (prevent: boolean) => void;
}

export const LegacyPage = React.memo(function LegacyPage(
  props: LegacyPageProps
) {
  const { location, updateTemplate, setPreventNavigation } = props;
  const [fullPageError, setFullPageError] = React.useState<ErrorResponse>();
  const page = React.useMemo(
    () => bridge.legacyUri(location.pathname + location.search),
    [location]
  );
  React.useEffect(() => setFullPageError(undefined), [location]);
  const { LegacyContent } = bridge;
  React.useEffect(() => {
    updateTemplate(templateDefaults(""));
    setPreventNavigation(false);
  }, []);

  function processError(err: { error: ErrorResponse; fullScreen: boolean }) {
    const { error, fullScreen } = err;
    if (fullScreen) {
      setFullPageError(error);
      updateTemplate(tp => ({ ...tp, title: error.error } as TemplateProps));
    } else {
      updateTemplate(templateError(error));
    }
  }

  return (
    <React.Fragment>
      {fullPageError && <ErrorPage error={fullPageError} />}
      <LegacyContent
        page={page}
        contentUpdated={content => {
          var soHtml = content.html["so"];
          var menuExtra = soHtml ? (
            <ScreenOptions optionsHtml={soHtml} contentId={content.contentId} />
          ) : (
            undefined
          );
          setPreventNavigation(content.preventUnload);
          updateTemplate(tp => ({
            ...tp,
            title: content.title,
            hideAppBar: content.hideAppBar,
            fullscreenMode: content.fullscreenMode as FullscreenMode,
            menuMode: content.menuMode as MenuMode,
            menuExtra
          }));
        }}
        userUpdated={props.refreshUser}
        redirected={redir => {
          const { href, external } = redir;
          if (external) {
            window.location.href = href;
          } else {
            const ind = href.indexOf("?");
            const redirloc =
              ind < 0
                ? { pathname: "/" + href, search: "" }
                : {
                    pathname: "/" + href.substr(0, ind),
                    search: href.substr(ind)
                  };
            props.redirect(redirloc);
          }
        }}
        onError={processError}
      />
    </React.Fragment>
  );
});
