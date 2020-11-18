import './register.css';
import React from 'react';
import {withRouter} from 'react-router-dom';
import profileSymb from '../../assets/profile.png';
import Logo from '../../assets/logo.png';

class Register extends React.Component{

    signin = () => {
        this.props.history.push("/login");
    }
    myFunction = () =>{
        alert("CLICKED");
    }

    render(){
        return(
            <div>

                <button onClick={this.myFunction}>
                    <img className = "logoImage" alt ="logo" src = {Logo}/>
                </button>

                <div className = "registerContainer">
                    
                    <div className="content">

                    <img className = "profileSymb" src={profileSymb} alt = "Profile"/> 
                    
                        <form>
                            <input
                                type="text"
                                placeholder="Login"
                                onChange={e => this.setState({ username: e.target.value })}
                            />
                            <input
                                type="text"
                                placeholder="RegistrationID"
                                onChange={e => this.setState({ registrationId: e.target.value })}
                            />
                            <input
                                type="password"
                                placeholder="Password"
                                onChange={e => this.setState({ password: e.target.value })}
                            />

                            <button className="button">Create</button>

                        </form>
                        <hr/>
                
                        <section>
                    
                            <p>Don't have an account yet?</p>
                    
                            <button onClick={this.signin} >Sign Up</button>
                    
                        </section>
                    </div>
                </div>
            </div>
        );
    }
}
  
export default withRouter(Register);