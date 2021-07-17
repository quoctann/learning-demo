const path = require('path');
const express = require('express');
const ejs = require('ejs');
const app = express();
let port = 3000;

// Set the view engine is ejs
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// Routing and render some template
app.get('/', function(req, res) {
    res.render('pages/index.ejs');
});

app.get('/about', function(req, res) {
    res.render('pages/about')
});

app.listen(port, () => {
    console.log('Server running...');
});
