import React from 'react';
import Child from './DemoContext2';

export const DemoContext = React.createContext();

export default class Parent extends React.Component {
    render() {
        return(
            <DemoContext.Provider value="Subject from parent">
                <Child/>
            </DemoContext.Provider>
        )
    }
}