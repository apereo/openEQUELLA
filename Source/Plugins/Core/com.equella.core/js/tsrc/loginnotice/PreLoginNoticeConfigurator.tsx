import * as React from "react";
import { ChangeEvent } from "react";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  FormControl,
  FormControlLabel,
  Grid,
  Radio,
  RadioGroup,
  Typography
} from "@material-ui/core";
import { DatePicker } from "material-ui-pickers";
import { commonString } from "../util/commonstrings";
import {
  clearPreLoginNotice,
  getPreLoginNotice,
  NotificationType,
  PreLoginNotice,
  ScheduleTypeSelection,
  strings,
  submitPreLoginNotice
} from "./LoginNoticeModule";
import { AxiosError, AxiosResponse } from "axios";
import RichTextEditor from "../components/RichTextEditor";
import SettingsMenuContainer from "../components/SettingsMenuContainer";

interface PreLoginNoticeConfiguratorProps {
  handleError: (axiosError: AxiosError) => void;
  notify: (notificationType: NotificationType) => void;
}

interface PreLoginNoticeConfiguratorState {
  preNotice: string;
  dbPreNotice: string;
  clearStaged: boolean;
  scheduleType: ScheduleTypeSelection;
  dbScheduleType: ScheduleTypeSelection;
  startDate?: Date;
  dbStartDate?: Date;
  endDate?: Date;
  dbEndDate?: Date;
}

class PreLoginNoticeConfigurator extends React.Component<
  PreLoginNoticeConfiguratorProps,
  PreLoginNoticeConfiguratorState
> {
  constructor(props: PreLoginNoticeConfiguratorProps) {
    super(props);
    this.state = {
      preNotice: "",
      dbPreNotice: "",
      clearStaged: false,
      scheduleType: ScheduleTypeSelection.ON,
      dbScheduleType: ScheduleTypeSelection.ON,
      startDate: new Date(),
      dbStartDate: new Date(),
      endDate: new Date(),
      dbEndDate: new Date()
    };
  }

  handleSubmitPreNotice = () => {
    if (this.state.preNotice != undefined) {
      let noticeToSend: PreLoginNotice = {
        notice: this.state.preNotice,
        scheduleSettings: this.state.scheduleType,
        startDate: this.state.startDate,
        endDate: this.state.endDate
      };
      submitPreLoginNotice(noticeToSend)
        .then(() => {
          this.props.notify(NotificationType.Save);
          this.setDBToValues();
        })
        .catch((error: AxiosError) => {
          this.props.handleError(error);
        });
    }
  };

  handleClearPreNotice = () => {
    clearPreLoginNotice()
      .then(() => {
        this.forceEditorRefresh();
        this.setState({
          clearStaged: false,
          preNotice: "",
          dbPreNotice: ""
        });
        this.props.notify(NotificationType.Clear);
      })
      .catch((error: AxiosError) => {
        this.props.handleError(error);
      });
  };

  handleUndoPreNotice = () => {
    this.setValuesToDB();
    this.forceEditorRefresh();
    this.props.notify(NotificationType.Revert);
  };

  forceEditorRefresh = () => {
    this.setState(
      {
        //swap the states to force an update
        preNotice: this.state.dbPreNotice,
        dbPreNotice: this.state.preNotice
      },
      () => this.setState({ dbPreNotice: this.state.preNotice })
    ); //set the dbPreNotice back to it's original value to update the editor
  };

  //   handlePreTextFieldChange = (
  //     e: HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement
  //   ) => {
  //     this.setState({ preNotice: e.value });
  //   };

  setValuesToDB = () => {
    this.setState({
      preNotice: this.state.dbPreNotice,
      scheduleType: this.state.dbScheduleType,
      startDate: this.state.dbStartDate,
      endDate: this.state.dbEndDate
    });
  };

  setDBToValues = () => {
    this.setState({
      dbPreNotice: this.state.preNotice,
      dbScheduleType: this.state.scheduleType,
      dbStartDate: this.state.startDate,
      dbEndDate: this.state.endDate
    });
  };

  componentDidMount = () => {
    getPreLoginNotice()
      .then((response: AxiosResponse<PreLoginNotice>) => {
        if (response.data.notice != undefined) {
          this.setState({
            dbPreNotice: response.data.notice,
            dbScheduleType: response.data.scheduleSettings,
            dbStartDate: response.data.startDate,
            dbEndDate: response.data.endDate
          });
          this.setValuesToDB();
        }
      })
      .catch((error: AxiosError) => {
        this.props.handleError(error);
      });
  };

  stageClear = () => {
    this.setState({ clearStaged: true });
  };

  Dialogs = () => {
    return (
      <div>
        <Dialog
          open={this.state.clearStaged}
          onClose={() => this.setState({ clearStaged: false })}
        >
          <DialogTitle>{strings.clear.title}</DialogTitle>
          <DialogContent>
            <DialogContentText>{strings.clear.confirm}</DialogContentText>
          </DialogContent>
          <DialogActions>
            <Button id="okToClear" onClick={this.handleClearPreNotice}>
              {commonString.action.ok}
            </Button>
            <Button
              id="cancelClear"
              onClick={() => this.setState({ clearStaged: false })}
            >
              {commonString.action.cancel}
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  };

  areButtonsEnabled = (): boolean => {
    //state matches database?
    return (
      this.state.scheduleType == this.state.dbScheduleType &&
      this.state.preNotice == this.state.dbPreNotice &&
      this.state.startDate == this.state.dbStartDate &&
      this.state.endDate == this.state.dbEndDate
    );
  };

  handleScheduleTypeSelectionChange = (event: ChangeEvent, value: string) => {
    this.setState({ scheduleType: ScheduleTypeSelection[value] });
  };

  handleStartDateChange = (value: Date) => {
    this.setState({ startDate: value });
  };

  handleEndDateChange = (value: Date) => {
    this.setState({ endDate: value });
  };

  resetDatePickers = () => {
    this.setState({ startDate: undefined, endDate: undefined });
  };

  ScheduleSettings = () => {
    return (
      <FormControl>
        <Typography color="textSecondary" variant="subheading">
          {strings.scheduling.title}
        </Typography>

        <RadioGroup
          row
          value={ScheduleTypeSelection[this.state.scheduleType]}
          onChange={this.handleScheduleTypeSelectionChange}
        >
          <FormControlLabel
            value={ScheduleTypeSelection[ScheduleTypeSelection.ON]}
            label={strings.scheduling.alwayson}
            control={<Radio id="onRadioButton" />}
          />
          <FormControlLabel
            value={ScheduleTypeSelection[ScheduleTypeSelection.SCHEDULED]}
            label={strings.scheduling.scheduled}
            control={<Radio id="scheduledRadioButton" />}
          />
          <FormControlLabel
            value={ScheduleTypeSelection[ScheduleTypeSelection.OFF]}
            label={strings.scheduling.disabled}
            control={<Radio id="offRadioButton" />}
          />
        </RadioGroup>

        <div
          hidden={this.state.scheduleType != ScheduleTypeSelection.SCHEDULED}
        >
          <Typography color="textSecondary" variant="subheading">
            {strings.scheduling.start}
          </Typography>

          <DatePicker
            id="startDatePicker"
            okLabel={<span id="ok">OK</span>}
            minDate={new Date().toLocaleDateString()}
            onChange={this.handleStartDateChange}
            value={
              this.state.startDate == undefined ? null : this.state.startDate
            }
          />

          <Typography color="textSecondary" variant="subheading">
            {strings.scheduling.end}
          </Typography>

          <DatePicker
            id="endDatePicker"
            minDate={this.state.startDate}
            minDateMessage={strings.scheduling.endbeforestart}
            onChange={this.handleEndDateChange}
            value={this.state.endDate == undefined ? null : this.state.endDate}
          />
        </div>
      </FormControl>
    );
  };

  handleEditorChange = (preNotice: string) => {
    this.setState({ preNotice });
  };

  render() {
    const Dialogs = this.Dialogs;
    const ScheduleSettings = this.ScheduleSettings;
    return (
      <SettingsMenuContainer>
        <Typography color="textSecondary" variant="subheading">
          {strings.prelogin.label}
        </Typography>
        <Grid id="preLoginConfig" container spacing={8} direction="column">
          <Grid item>
            <RichTextEditor
              htmlInput={this.state.dbPreNotice}
              onStateChange={this.handleEditorChange}
            />
          </Grid>
          <Grid item>
            <ScheduleSettings />
          </Grid>
          <Grid item container spacing={8} direction="row-reverse">
            <Grid item>
              <Button
                id="preApplyButton"
                disabled={this.areButtonsEnabled()}
                onClick={this.handleSubmitPreNotice}
                variant="contained"
              >
                {commonString.action.save}
              </Button>
            </Grid>
            <Grid item>
              <Button
                id="preClearButton"
                onClick={this.stageClear}
                variant="text"
              >
                {commonString.action.clear}
              </Button>
            </Grid>
            <Grid item>
              <Button
                id="preUndoButton"
                disabled={this.areButtonsEnabled()}
                onClick={this.handleUndoPreNotice}
                variant="text"
              >
                {commonString.action.revertchanges}
              </Button>
            </Grid>
          </Grid>
        </Grid>
        <Dialogs />
      </SettingsMenuContainer>
    );
  }
}

export default PreLoginNoticeConfigurator;
