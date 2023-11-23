import PropTypes from "prop-types"
const NewGame = ({ handleCreateGame }) => {

    return (
        <>
            <div className="NewGameButton" onClick={() => { handleCreateGame() }}>New Game</div>
        </>
    )
}
NewGame.propTypes = {
    handleCreateGame: PropTypes.func
}
export default NewGame;