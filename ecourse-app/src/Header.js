import React, { useContext, useEffect, useState } from "react";
import API, { endpoints } from "./API";
import { Nav, Navbar, NavDropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import cookies from "react-cookies";
// import { UserContext } from "./App";
import { useStore } from "react-redux";

export default function Header(props) {
	const [categories, setCategories] = useState([]);
	// const auth = useContext(UserContext);
	const store = useStore();
	const auth = store.getState();

	useEffect(() => {
		API.get(endpoints["categories"]).then((res) => {
			setCategories(res.data);
		});
	});

	// let user = auth.user;
	let user = auth;
	if (cookies.load("user") != null)
		user = cookies.load("user");
	let r = (
		<>
			<Nav.Link href="/login">Đăng nhập</Nav.Link>
			<Nav.Link href="/register">Đăng ký</Nav.Link>
		</>
	);
	if (user != null) r = <Nav.Link href="/">{user.username}</Nav.Link>;

	return (
		<>
			<Navbar bg="light" expand="lg">
				<Navbar.Brand>React Demo</Navbar.Brand>
				<Navbar.Toggle aria-controls="basic-navbar-nav" />
				<Navbar.Collapse id="basic-navbar-nav">
					<Nav className="mr-auto">
						<Nav.Link>
							<Link to="/">Trang chủ</Link>
						</Nav.Link>
						{r}
						{/* <Nav.Link><Link to="/register">Đăng ký 'to'</Link></Nav.Link> */}
						{categories.map((c) => (
							<Nav.Link href="#link">{c.name}</Nav.Link>
						))}
					</Nav>
					<NavDropdown title="Dropdown">
						<ul>
							<li>
								<Link to="/">Home</Link>
							</li>
							<li>
								<Link to="/lesson">Lesson</Link>
							</li>
							<li>
								<Link to="/courses/2/lessons">
									Khóa học số 2
								</Link>
							</li>
						</ul>
					</NavDropdown>
				</Navbar.Collapse>
			</Navbar>
		</>
	);
}
