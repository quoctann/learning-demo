import React, { useState } from 'react';
import { Container } from 'react-bootstrap';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import './App.css';
import Body from './Body';
import CourseLesson from './CourseLesson';
import Header from './Header';
import Lesson from './Lesson';
import Login from './Login';
import Register from './Register';
import API, { endpoints } from './API';
import cookies from 'react-cookies';
import { useDispatch } from 'react-redux';

export let UserContext = React.createContext();

export default function App(props) {
  const [user, setUser] = useState(null);
  const dispatch = useDispatch();
  const login = async (username, password) => {
    let res = await API.post(endpoints["login"], {
      client_id: "yo04kF4wcy2OFsv7BZRS26FeX4e6EnlRQhIZjpCU",
      client_secret: "hvwJZgKrH2IcO0z78WKPhPhrCL6Th4K6i7Y5COEVlkj3pYOOMheK9GYWsXk1ulMDFILjij4xlvx9D7awJW32c3kIMd88Gxh4I8HH2HCxyL8NmUl3fJAVtzKr3lCAkM0A",
      username: username,
      password: password,
      grant_type: "password",
    });

    console.info(res.data);
    cookies.save("access_token", res.data.access_token);

    let user = await API.get(endpoints["current-user"], {
      headers: {
        Authorization: `Bearer ${cookies.load("access_token")}`,
      },
    });
    // console.info(user.data);
    cookies.save("user", user.data);
    dispatch({
      "type": "login",
      "payload": user.data
    })
    setUser(user);
  };
    return (
      // <UserContext.Provider value={{user, login}}>
        <BrowserRouter>
        <Container>
          <Header/>
          <Switch>
            <Route exact path='/' component={Body}/>
            <Route exact path='/lesson' component={Lesson}/>
            <Route exact path='/courses/:courseId/lessons' component={CourseLesson} />
            <Route exact path='/register' component={Register}/>
            <Route exact path='/login' component={Login}/>
          </Switch>
          </Container>
        </BrowserRouter>
      // </UserContext.Provider>
    )
}
// Logout thì gọi cookies xóa access token & user