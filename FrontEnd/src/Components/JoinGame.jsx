import { useState } from "react";
import PropTypes from "prop-types"
import { gameCodeChecks } from "../Utils/boardUtils";

const JoinGame = ({ handleJoinGame }) => {
    const [gameCode, setGameCode] = useState("");
    const handleSubmit = () => {
        event.preventDefault()
        handleJoinGame(gameCode);
    }
    return (
        <>
            <form aria-label="form" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="gameId">Enter game code:&nbsp;</label>
                    <input
                        type="text"
                        name="gameId"
                        placeholder=""
                        className="form-control"
                        value={gameCode}
                        onChange={event => setGameCode(event.target.value)}
                    />
                </div>
                <div className="form-group">
                    <input type="submit" value="Submit" className={`btn-primary`} disabled={gameCodeChecks(gameCode)} />
                </div>
            </form>
        </>
    )
}

JoinGame.propTypes = {
    handleJoinGame: PropTypes.func
}

export default JoinGame;