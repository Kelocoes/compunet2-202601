import { useEffect, useState } from "react"
import { useNavigate } from "react-router"
import { Box, Card, CardContent, Container, Stack, Typography } from "@mui/material"
import gamesService from "../../services/games/games.service"
import GameCard from "../../components/GameCard"

export default function Dashboard() {
    const nav = useNavigate()
    const [games, setGames] = useState([])

    useEffect(() => {
        const fn = async () => {
            const token = localStorage.getItem('token')
            if (!token) {
                alert('You must be logged in to access the dashboard.')
                nav('/signin')
            }

            const result = await gamesService.getAll()
            if (result.error) {
                alert('Session expired. Please log in again.')
                localStorage.removeItem('token')
                nav('/signin')
            }

            setGames(result.data)
        }

        fn()
    }, [])


    return (
        <Box sx={{ minHeight: '100vh', display: 'grid', placeItems: 'center', px: 2 }}>
            <Container maxWidth="sm">
                {games.length > 0 ? (
                    <Box sx={{ width: "100%" }}>
                        <Stack spacing={2}>
                            {games.map((game: any) => (
                                <GameCard key={game.id} game={game} />
                            ))}
                        </Stack>
                    </Box>
                ) : (
                    <Card variant="outlined">
                        <CardContent>
                            <Typography variant="body1" align="center">
                                No hay juegos disponibles.
                            </Typography>
                        </CardContent>
                    </Card>
                )}
            </Container>
        </Box>
    )
}