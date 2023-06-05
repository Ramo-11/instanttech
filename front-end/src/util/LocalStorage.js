import { useEffect, useState } from "react"

function useLocalState(defaultValue, key) {
    const [value, setValue] = useState(() => {
        const lcoalValue  = localStorage.getItem(key)

        return lcoalValue !== null ? JSON.parse(lcoalValue) : defaultValue
    })

    useEffect(() => {
        localStorage.setItem(key, JSON.stringify(value))
    }, [key, value])

    return [value, setValue]
}

export { useLocalState}