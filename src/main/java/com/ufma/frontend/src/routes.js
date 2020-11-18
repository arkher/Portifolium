import React from 'react'
import {Route, Switch, HashRoute, HashRouter} from 'react-router-dom';
import Register from './pages/Register/register.js';
import Login from './pages/Logon/login.js';

function Rotas(){
    return(
        <HashRouter>
            <Switch>
                <Route path="/login" component={Login}/>
                <Route path="/register" component={Register}/>
            </Switch>
        </HashRouter>
    );
}

export default Rotas;