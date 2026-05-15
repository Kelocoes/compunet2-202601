import { Card, CardContent, Typography } from '@mui/material';

export default function GameCard({ game }) {
    return (

        <Card key={game.id} variant="outlined">
            <CardContent>
                <Typography variant="h6" gutterBottom>
                    {game.name}
                </Typography>

                <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
                    {game.description}
                </Typography>

                <Typography variant="body2">
                    Categoría: {game.category}
                </Typography>

                <Typography variant="body2">
                    Jugadores: {game.minPlayers} - {game.maxPlayers}
                </Typography>

                <Typography variant="body2">
                    Creador: {game.username}
                </Typography>
            </CardContent>
        </Card>
    );
}