import React from 'react'
import './Counter.css';

function CounterButton(props) {

    const onAdd = (value) => {
        props.increment(value);
    }

    const onDec = (value) => {
        props.decrement(value);
    }
    return (
        <div className="counter">
            <button onClick={e => onAdd(props.by)}>+{props.by}</button>
            <button onClick={e => onDec(props.by)}>-{props.by}</button>
        </div>
    )
}



export default CounterButton;