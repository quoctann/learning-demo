import React from 'react';
import { DemoContext } from './DemoContext1';

export default class Child extends React.Component {
    render() {
        return(
            <>
                <h2>Child</h2>
                <DemoContext.Consumer>
                    {value => <h3>{value}</h3>}
                </DemoContext.Consumer>
            </>
        )
    }
}