import Tile from '../Components/Tile.jsx'

export const createBoard = () => {
    let tiles = [];
    for (let y = 1; y <= 8; y++) {
        tiles = [...tiles, createRow(y)]
    }
    return tiles;
}

const createRow = (y) => {
    let row = [];
    for (let x = 1; x <= 8; x++) {
        row = [...row, createTile(x, y)]
    }
    return row;
}

const createTile = (x, y) => {
    const yPos = y;
    const xPos = posToLetter(x)
    return <Tile xPos={xPos} yPos={yPos} key={`${xPos} ${yPos}`} />;
}

export const getTileColour = (xPos, yPos) => {
    const x = letterToPos(xPos);
    const y = yPos;
    return POS_TO_COLOUR[(x + y) % 2];
}

const POS_TO_COLOUR = ['black', 'white'];

const POS_TO_LETTER = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];

const posToLetter = (pos) => {
    return POS_TO_LETTER[pos - 1];
}

const letterToPos = (letter) => {
    return POS_TO_LETTER.indexOf(letter) + 1;
}