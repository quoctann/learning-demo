import React from 'react';
import { Container } from 'react-bootstrap';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import './App.css';
import Body from './Body';
import CourseLesson from './CourseLesson';
import Header from './Header';
import Lesson from './Lesson';
import Register from './Register';

class App extends React.Component {
  
  render() {
    return(
      <BrowserRouter>
      <Container>
        <Header/>
        <Switch>
          <Route exact path='/' component={Body}/>
          <Route exact path='/lesson' component={Lesson}/>
          <Route exact path='/courses/:courseId/lessons' component={CourseLesson} />
          <Route exact path='/register' component={Register}/>
        </Switch>
        </Container>
      </BrowserRouter>
    )
  }
}

export default App;
