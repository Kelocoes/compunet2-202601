export default function CustomButton({ text, color, handleClick }) {
    return (
        <button style={{ backgroundColor: color }}
            onClick={handleClick}
        >
            {text}
        </button>
    )
}