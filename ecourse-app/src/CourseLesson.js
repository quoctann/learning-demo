import React from 'react';

class CourseLesson extends React.Component {
    render() {
        return(
            <h1>This is a course: {this.props.match.params.courseId}</h1>
        )
    }
}

export default CourseLesson;
