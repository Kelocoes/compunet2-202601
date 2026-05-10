import { useState } from 'react';

const images = [
    {
        url: 'https://picsum.photos/id/1015/800/500',
        alt: 'Paisaje 1',
    },
    {
        url: 'https://picsum.photos/id/1025/800/500',
        alt: 'Paisaje 2',
    },
    {
        url: 'https://picsum.photos/id/1035/800/500',
        alt: 'Paisaje 3',
    },
    {
        url: 'https://picsum.photos/id/1043/800/500',
        alt: 'Paisaje 4',
    },
];

export default function Carrousel() {
    const [index, setIndex] = useState(0);
    const hasNext = index < images.length - 1;

    function handleNextClick() {
        if (hasNext) {
            setIndex(index + 1);
        } else {
            setIndex(0);
        }
    }

    function handlePrevClick() {
        if (index > 0) {
            setIndex(index - 1);
        } else {
            setIndex(images.length - 1);
        }
    }

    const image = images[index];

    return (
        <>
            <button onClick={handlePrevClick}>Prev</button>
            <button onClick={handleNextClick}>Next</button>

            <h2>
                Imagen {index + 1} de {images.length}
            </h2>

            <img
                src={image.url}
                alt={image.alt}
                style={{ width: '100%', maxWidth: '800px', display: 'block' }}
            />
        </>
    );
}
