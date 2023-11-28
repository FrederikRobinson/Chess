import { useState } from "react";
import { loginChecks, emailChecks, usernameChecks, passwordChecks } from "../Utils/loginUtils.jsx";
import PropTypes from "prop-types"
const SignUp = ({ signUpHandler }) => {

    const [username, setUsername] = useState("");
    const [usernameDirt, setUsernameDirt] = useState(false);
    const [usernameError, setUsernameError] = useState(false);
    const [password, setPassword] = useState("");
    const [passwordDirt, setPasswordDirt] = useState(false);
    const [passwordError, setPasswordError] = useState(false);
    const [email, setEmail] = useState("");
    const [emailDirt, setEmailDirt] = useState(false);
    const [emailError, setEmailError] = useState(false);
    const handleSubmit = () => {
        signUpHandler(username, password, email);
        setUsername("");
        setPassword("");
        setEmail("");
    }
    return (<>
        <div className="pageTitle">Sign Up</div>
        <form aria-label="form" onSubmit={(event) => { event.preventDefault(); handleSubmit(); }}>
            <div className="form-group">
                <label htmlFor="username">Enter Username:&nbsp;</label>
                <input
                    type="text"
                    name="username"
                    placeholder="Username"
                    className="form-control"
                    value={username}
                    onChange={event => setUsername(event.target.value)}
                    onBlur={event => { setUsernameDirt(true); setUsernameError(usernameChecks(event.target.value)); }}
                />
            </div>
            {usernameError && usernameDirt && <div className="text-danger">Please enter a username</div>}
            <div className="form-group">
                <label htmlFor="email">Enter Email:&nbsp;</label>
                <input
                    type="text"
                    name="email"
                    placeholder="Email"
                    className="form-control"
                    value={email}
                    onChange={event => setEmail(event.target.value)}
                    onBlur={event => { setEmailDirt(true); setEmailError(!emailChecks(event.target.value)); }}

                />
            </div>
            {emailError && emailDirt && <div className="text-danger">Invalid email</div>}
            <div className="form-group">
                <label htmlFor="password">Enter Password:&nbsp;</label>
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    className="form-control"
                    value={password}
                    onChange={event => setPassword(event.target.value)}
                    onBlur={event => { setPasswordDirt(true); setPasswordError(passwordChecks(event.target.value)); }}
                />
            </div>
            {passwordError && passwordDirt && <div className="text-danger">Please enter a password</div>}
            <div className="form-group">
                <input type="submit" value="Submit" className={`btn-primary`} disabled={loginChecks(username, password)} />
            </div>
        </form>
    </>
    )
}
SignUp.propTypes = {
    handleCreateGame: PropTypes.func
}
export default SignUp