import { useRef, useState } from 'react'
import {
    Box,
    Button,
    Container,
    Paper,
    Stack,
    TextField,
    Typography,
} from '@mui/material'
import CustomButton from '../../components/CustomButton'

function Sandbox() {
    const [count, setCount] = useState(0)
    const [name, setName] = useState('')
    const inputRef = useRef(null)
    const [refValue, setRefValue] = useState('')

    const handleReadRef = () => {
        setRefValue(inputRef.current?.value ?? '')
    }

    return (
        <Box sx={{ minHeight: '100vh', display: 'grid', placeItems: 'center', px: 2 }}>
            <Container maxWidth="sm">
                <Paper elevation={3} sx={{ p: { xs: 3, md: 4 } }}>
                    <Stack spacing={3}>
                        <Box>
                            <Typography variant="h4" component="h1" fontWeight={700}>
                                Sandbox
                            </Typography>
                        </Box>

                        <Stack direction="row" spacing={2} alignItems="center">
                            <Button variant="contained" onClick={() => setCount((value) => value + 1)}>
                                Counter: {count}
                            </Button>
                            <Typography variant="body2" color="text.secondary">
                                Cada click cambia el state.
                            </Typography>
                        </Stack>

                        <TextField
                            label="Escribe tu nombre"
                            value={name}
                            onChange={(event) => setName(event.target.value)}
                            fullWidth
                        />
                        <Typography variant="body2" color="text.secondary">
                            useState actualiza en cada tecla: {name || 'vacío'}
                        </Typography>

                        <TextField inputRef={inputRef} label="useRef input" fullWidth />
                        <Button variant="outlined" onClick={handleReadRef}>
                            Leer useRef
                        </Button>
                        <Typography variant="body2" color="text.secondary">
                            useRef solo se lee al hacer clic: {refValue || 'vacío'}
                        </Typography>

                        <Box>
                            <CustomButton
                                text="Botón personalizado"
                                color="#4ade80"
                                handleClick={() => alert('¡Hola desde el botón personalizado!')}
                            />
                        </Box>
                    </Stack>
                </Paper>
            </Container>
        </Box>
    )
}

export default Sandbox

