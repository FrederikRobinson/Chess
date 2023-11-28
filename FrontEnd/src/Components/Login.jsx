import { useState } from "react"
import { loginChecks, usernameChecks, passwordChecks } from "../Utils/loginUtils";
import PropTypes from "prop-types"
const Login = ({ loginHandler }) => {

    const [username, setUsername] = useState("");
    const [usernameDirt, setUsernameDirt] = useState(false);
    const [usernameError, setUsernameError] = useState(false);
    const [password, setPassword] = useState("");
    const [passwordDirt, setPasswordDirt] = useState(false);
    const [passwordError, setPasswordError] = useState(false);
    const handleSubmit = () => {
        // event.preventDefault()
        loginHandler(username, password);
        setUsername("");
        setPassword("");
    }
    return (<>
        <div className="pageTitle">Login</div>
        <form aria-label="form" onSubmit={(event) => { event.preventDefault(); handleSubmit() }}>
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
Login.propTypes = {
    loginHandler: PropTypes.func
}
export default Login