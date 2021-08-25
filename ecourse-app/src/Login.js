import React, { useContext } from "react";
import { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { Redirect } from "react-router-dom";
import { UserContext } from "./App";

export default function Login(props) {
	const [username, setUsername] = useState(null);
	const [password, setPassword] = useState(null);
    const [isLogged, setLogged] = useState(false);
    const auth = useContext(UserContext);
    // Thay thế và dùng chung luôn cho comp didmount diđupate các thứ
    // useEffect(() => {
    //     console.info("test")
    //     console.info(Math.random())
    // })

    const login = async (event) => {
        event.preventDefault();
        auth.login(username, password);
        setLogged(true);
    }

    if (isLogged)
        return <Redirect to="/"/>
    else
        return (
            <>
                <h1 className="text-center text-danger my-3">Đăng nhập</h1>
                <p>Hello {username}</p>
                <Form onSubmit={login}>
                    <LoginForm id="username" type="text" field={username} change={(event) => setUsername(event.target.value)}/>
                    <LoginForm id="password" type="password" field={password} change={(event) => setPassword(event.target.value)}/>
                    <Button type="submit">Login</Button>
                </Form>
            </>
        );
}

class LoginForm extends React.Component {
	render() {
		return (
			<Form.Group className="mb-3" controlId={this.props.id}>
				<Form.Label>{this.props.label}</Form.Label>
				<Form.Control
					type={this.props.type}
					value={this.props.field}
					onChange={this.props.change}
				/>
			</Form.Group>
		);
	}
}
