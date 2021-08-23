import React from "react";
import API, { endpoints } from "./API";
import { Nav, Navbar, NavDropdown } from "react-bootstrap";
import { Link } from 'react-router-dom'
import "bootstrap/dist/css/bootstrap.min.css";

class Header extends React.Component {
	constructor() {
		super();
		this.state = { categories: [] };
	}

	componentDidMount() {
		API.get(endpoints["categories"]).then((res) => {
			console.info(res);
			this.setState({
				categories: res.data,
			});
		});
	}
	// https://youtu.be/3CpUtqhRdh0?list=PLlVHoHHccp2_jIce6hcp5WhNhEvc6NYrQ&t=1535

	render() {
		return (
			<>
				<Navbar bg="light" expand="lg">
					<Navbar.Brand>React Demo</Navbar.Brand>
					<Navbar.Toggle aria-controls="basic-navbar-nav" />
					<Navbar.Collapse id="basic-navbar-nav">
						<Nav className="mr-auto">
							<Nav.Link><Link to="/">Trang chủ</Link></Nav.Link>
							<Nav.Link><Link to="/register">Đăng ký 'to'</Link></Nav.Link>
							<Nav.Link href="/register">Đăng ký</Nav.Link>
							{this.state.categories.map((c) => (
								<Nav.Link href="#link">{c.name}</Nav.Link>
							))}
						</Nav>
						<NavDropdown title='Dropdown'>
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
}

export default Header;
