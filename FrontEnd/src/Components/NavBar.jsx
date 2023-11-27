import { NavLink } from "react-router-dom"
import PropTypes from "prop-types"

function NavBar({ errorDisplay, userId, logout }) {
    const loggedIn = userId !== 0;

    return (
        <>
            <header>
                <nav id="navbar" className="navbar navbar-expand-lg navbar-light bg-light">
                    <div className="container-fluid">
                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav m-auto mb-2 mb-lg-0">
                                <li className="nav-item">
                                    <NavLink to="/">Home</NavLink>
                                </li>
                                {loggedIn &&
                                    <li className="nav-item">
                                        <NavLink to="/play">New Game</NavLink>
                                    </li>}
                                {!loggedIn && <li className="nav-item">
                                    <NavLink to="/login">Login</NavLink>
                                </li>}
                                {!loggedIn && <li className="nav-item">
                                    <NavLink to="/signUp">Sign Up</NavLink>
                                </li>}
                                {loggedIn && <li className="nav-item">
                                    <NavLink to="/" onClick={logout} >Logout</NavLink>
                                </li>}
                                {/* {loggedIn && <li className="nav-item">
                                    <NavLink to="/joinGame">Join Game</NavLink>
                                </li>} */}
                            </ul>
                        </div>
                    </div>
                </nav>
                <div className="errorDisplay">{errorDisplay}</div>
            </header>
        </>
    )
}

NavBar.propTypes = {
    errorDisplay: PropTypes.string,
    userId: PropTypes.number,
    logout: PropTypes.func
}


export default NavBar
