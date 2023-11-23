import { useState } from "react";
import { loginChecks } from "../Utils/boardUtils.jsx";
import PropTypes from "prop-types"
const SignUp = ({ signUpHandler }) => {
    event.preventDefault();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const handleSubmit = () => {
        signUpHandler(username, password);
        setUsername("");
        setPassword("");
        setEmail("");
    }
    return (
        <form aria-label="form" onSubmit={handleSubmit}>
            <div className="form-group">
                <label htmlFor="username">Enter Username:&nbsp;</label>
                <input
                    type="text"
                    name="username"
                    placeholder="Username"
                    className="form-control"
                    value={username}
                    onChange={event => setUsername(event.target.value)}
                />
            </div>
            <div className="form-group">
                <label htmlFor="email">Enter Email:&nbsp;</label>
                <input
                    type="text"
                    name="email"
                    placeholder="email"
                    className="form-control"
                    value={email}
                    onChange={event => setEmail(event.target.value)}
                />
            </div>
            <div className="form-group">
                <label htmlFor="password">Enter Password:&nbsp;</label>
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    className="form-control"
                    value={password}
                    onChange={event => setPassword(event.target.value)}
                />
            </div>
            <div className="form-group">
                <input type="submit" value="Submit" className={`btn-primary`} disabled={!loginChecks(username, password)} />
            </div>
        </form>
    )
}
NewGame.propTypes = {
    handleCreateGame: PropTypes.func
}
export default SignUp