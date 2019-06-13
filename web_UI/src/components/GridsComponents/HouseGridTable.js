import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchHouseGrids, fetchHouseGridDetails } from '../../actions/actionsHouseGrid';
import TableHeaderHouseGrid from './TableHeaderHouseGrid';
import TableBodyHouseGrid from './TableBodyHouseGrid';

class HouseGridTable extends Component {
    constructor(props) {
        super(props);
    }
    componentDidMount() {
        this.props.onFetchHouseGrids();
    }
    fetchHouseGridDetails = (id) => {
        this.props.onFetchHouseGridDetails(id);
    }

    render() {

        const headers = {
            id: "ID",
            name: "Description",
        };
        const { loading, error, data } = this.props.houseGrids;
        const houseGridId = this.props.houseGridId;
        const houseGridName = this.props.hgRooms;

        if (loading === true) {
            return (<h1>Loading ....</h1>);
        }
        else {
            if (error !== null) {
                return (<h1>Error ....</h1>);
            } else {
                if (data.length > 0) {
                    return (
                        <table >
                            <TableHeaderHouseGrid headers={headers} />
                            <TableBodyHouseGrid data={data} getDetailsHouseGrid={this.fetchHouseGridDetails} houseGridId={houseGridId}/>
                        </table>
                    );
                } else {
                    return (<h1>No data ....</h1>);
                }
            }
        }
    }
}


const mapStateToProps = (state) => {
    return {
        houseGrids: {
            loading: state.houseGrids.loading,
            data: state.houseGrids.data,
            error: state.houseGrids.error,
        },
        houseGridId: state.details.houseGridId,
        hgRooms: state.details.hgRoom

    }
}
const mapDispatchToProps = (dispatch) => {
    return {
        onFetchHouseGrids: () => {
            dispatch(fetchHouseGrids())
        },
        onFetchHouseGridDetails: (id) => {
            dispatch(fetchHouseGridDetails(id))
        }
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(HouseGridTable);

