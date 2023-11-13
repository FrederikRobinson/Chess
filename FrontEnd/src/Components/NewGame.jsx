const NewGame = ({ handleCreateGame }) => {

    return (
        <>
            <div className="NewGameButton" onClick={() => { handleCreateGame() }}>New Game</div>
        </>
    )
}
export default NewGame;