import { useState } from "react"
import { loginChecks } from "../Utils/boardUtils";

const Login = ({ loginHandler }) => {
    event.preventDefault()
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const handleSubmit = () => {
        loginHandler(username, password);
        setUsername("");
        setPassword("");
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
export default Login