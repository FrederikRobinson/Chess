import { getTileColour } from "../Utils/boardUtils.jsx";

const Tile = ({ xPos, yPos }) => {
    const tileColour = getTileColour(xPos, yPos);

    return (
        <>
            <div className={`${tileColour} tileContainer`}>{xPos}{yPos}</div>
        </>
    )
}

export default Tile;