import { getTileColour } from "../Utils/boardUtils.jsx";
import PropTypes from "prop-types";

const Tile = ({ xPos, yPos, piece, functions }) => {
    const tileColour = getTileColour(xPos, yPos);

    return (
        <>
            <div className={`${tileColour} x${xPos} y${yPos} tileContainer`} onClick={() => functions.handleTileSelection(xPos, yPos)}>
                <div className={"tileContent"} >{xPos}{yPos}{piece[0]}{piece[1]}</div>
            </div>
        </>
    )
}

// Tile.propTypes = {
//     xPos: PropTypes.string.isRequired,
//     yPos: PropTypes.number.isRequired,
//     piece: PropTypes.arrayOf(
//         PropTypes.string
//     ).isRequired
// }

export default Tile;