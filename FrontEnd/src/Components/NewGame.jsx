import PropTypes from "prop-types"
const NewGame = ({ handleCreateGame }) => {

    return (
        <><div className="pageTitle">New Game</div>
            <button className="newGameButton" onClick={() => { handleCreateGame() }}>Create Game</button>
        </>
    )
}
NewGame.propTypes = {
    handleCreateGame: PropTypes.func
}
export default NewGame;