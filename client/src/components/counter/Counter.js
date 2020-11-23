import React, { useState } from 'react'
import CounterButton from './CounterButton';
export default function Counter() {
    const [count, setCount] = useState(0);
    const increment = (value) => {
        setCount(count + parseInt(value));
    }
    const decrement = (value) => {
        setCount(count - parseInt(value));
    }

    const reset = () => {
        setCount(0);
    }
    return (
        <React.Fragment>
            <div className="counter">
                <CounterButton decrement={decrement} increment={increment} by="1" />
                <CounterButton decrement={decrement} increment={increment} by="5" />
                <CounterButton decrement={decrement} increment={increment} by="10" />
            </div>
            <h1>{count}</h1>
            <button onClick={reset}>Reset</button>
        </React.Fragment>
    )
}