
import React from 'react'
import UserInfo from './UserInfo';


class User extends React.Component {
    constructor(props) {
        super(props);
        this.users = [
            {"id": 1, "name": "Tan"},
            {"id": 2, "name": "Vjp"},
            {"id": 3, "name": "Pro"},
        ]

        this.state = {
            "name": ""
        }

        // this.hello = this.hello.bind(this)
    }
    
    hello = (country) => {
        alert(`Welcome ${this.props.firstName} ${this.props.lastName} ${country}`);
    }

    change = (event) => {
        this.setState({
            "name": event.target.value
        })
    }

    render() {
        return (
            <React.Fragment>
                <h1>Bọc lại bằng thẻ <> </> cũng được</h1>
                <h3>Welcome {this.props.firstName} {this.props.lastName}</h3>
                <ul>
                    {this.users.map(u => <UserInfo user={u} />)}
                </ul>                
                <h1>Welcome {this.state.name}</h1>
                <form>
                    <input type="text" value={this.state.name} onChange={this.change}/>
                    
                    <input type="submit" value="Submit" onChange={this.change}/>
                </form>
                <input type="button" value="Click" onClick={() => this.hello("Vietnam")}/>
                {/* Nếu không truyền bằng arrow function thì phải dùng dạng this.hello.bing(this, "Vietnam") */}
            </React.Fragment>
        )
    }
}

export default User;