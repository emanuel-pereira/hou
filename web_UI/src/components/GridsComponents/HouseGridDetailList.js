import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchHouseGridDetails } from '../../actions/actionsHouseGrid';

class HouseGridDetailList extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        const { loading, error, data } = this.props.details;
        if (loading === true) {
            return (<h1>Loading ....</h1>);
        }
        else {
            if (error !== null) {
                return (<h1>Error ....</h1>);
            } else {
                if (data.floor>0) {
                    return (
                        <div>
                            <h2>{data.id} - {data.designation}</h2>
                        </div>
                    );
                }else{
                    return null;
                }
            }
        }
    }
}

const mapStateToProps = (state) => {
    return {
        details: {
            loading: state.details.loading,
            data: state.details.data,
            error: state.details.error,
            id: state.details.id,
        }
    }
}
const mapDispatchToProps = (dispatch) => {
    return {
        onFetchHouseGridDetails: (id) => {
            dispatch(fetchHouseGridDetails(id))
        }
    }
}


export default connect(
    mapStateToProps,
    null
)(HouseGridDetailList);
