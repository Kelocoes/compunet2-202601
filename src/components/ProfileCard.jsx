import { Button, Card, Typography } from "@mui/material";
import { useState } from "react";

export default function ProfileCard({ name, description, likes }) {
    console.log("Componente pintandose!");
    const [likesCounter, setLikesCounter] = useState(likes);
    // let likesCounter = likes;

    const increaseLikes = () => {
        console.log("He dado like!");
        setLikesCounter(prev => prev + 1);
        setLikesCounter(prev => prev + 2);
        setLikesCounter(prev => prev + 3);
        console.log(likesCounter);
    };

    return (
        <Card
            style={{
                padding: 10,
                width: "300px",
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center",
                margin: 10
            }}
            elevation={20}
        >
            <Typography style={{ fontWeight: "bold" }}>{name}</Typography>
            <Typography>{description}</Typography>
            <Typography>Likes: {likesCounter ?? 0}</Typography>
            <Button
                variant="contained"
                onClick={increaseLikes}
            >
                Likes!
            </Button>
        </Card>
    );
}