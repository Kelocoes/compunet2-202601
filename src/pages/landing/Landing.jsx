import { Box, Button, Container, Stack, Typography } from '@mui/material'
import { Link as RouterLink } from 'react-router'

function Landing() {
    return (
        <Box
            sx={{
                minHeight: '100vh',
                display: 'grid',
                placeItems: 'center',
                px: 2,
            }}
        >
            <Container maxWidth="md">
                <Box
                    sx={{
                        p: { xs: 4, md: 6 },
                        bgcolor: 'white',
                    }}
                >
                    <Stack spacing={3}>
                        <Typography variant="overline" color="primary">
                            CompuNet Front
                        </Typography>
                        <Typography variant="h3" component="h1" fontWeight={700}>
                            Landing simple con React y MUI.
                        </Typography>
                        <Typography color="text.secondary">
                            Una página mínima para navegar al login y al sandbox donde se ven
                            los conceptos básicos de state, ref y formularios.
                        </Typography>
                        <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                            <Button component={RouterLink} to="/signin" variant="contained">
                                Ir al login
                            </Button>
                            <Button component={RouterLink} to="/sandbox" variant="outlined">
                                Ver sandbox
                            </Button>
                        </Stack>
                    </Stack>
                </Box>
            </Container>
        </Box>
    )
}

export default Landing