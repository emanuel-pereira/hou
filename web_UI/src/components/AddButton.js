import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Icon from '@material-ui/core/Icon';
import {lightBlue} from "@material-ui/core/colors";

const useStyles = makeStyles(theme => ({
    root: {
        display: 'inline-table',
        justifyContent: 'center',
        alignItems: 'flex-end',
    },
    icon: {
        margin: theme.spacing(2),
    },
    iconHover: {
        margin: 0,
        '&:hover': {
            color: lightBlue[800],
        },
    },
}));

export default function Icons() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <Icon className={classes.iconHover} htmlColor="white" style={{ fontSize: 35 }}>
                add_circle
            </Icon>

        </div>
    );
}