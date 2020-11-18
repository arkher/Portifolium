import './login.css';
import React from 'react';
import {withRouter} from 'react-router-dom';
import Logo from '../../assets/logo.png'

class Login extends React.Component{
  signup = () =>{
    this.props.history.push("/register");
  }

  myFunction = ()=>{
    alert("CLICKED");
  }

  render(){
    return (
      <div>
        <button onClick={this.myFunction}>
            <img className="logoImage" alt ="logo" src = {Logo}/>
        </button>
        <div className = "loginContainer">
          
          <div className = "content">
            
            <form>
            
              <div>
              
                <input
                    type="text"
                    placeholder="Login"
                    onChange={e => this.setState({ username: e.target.value })}
                  />
            
              </div>
            
              <div>
                  <input
                    type="password"
                    placeholder="Password"
                    onChange={e => this.setState({ password: e.target.value })}
                  />
              
              </div>
            
              <div>
                  <button className="button">Sign In</button>
              
              </div>
            
              </form>
            
              <hr/>
              
              <section>
            
                <p>Don't have an account yet?</p>
            
                <button onClick={this.signup} >Sign Up</button>
            
              </section>
            </div>
        </div>
      </div>
    );
  }
}

export default withRouter(Login);