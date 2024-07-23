import React, { useState } from "react";
import "./App.css";
import bigInt from "big-integer";

function App() {
  const [number, setNumber] = useState("");
  const [result, setResult] = useState(null);

  const calculateLabseq = async (n) => {
    try {
      const response = await fetch(`http://localhost:8080/labseq/${n}`);
      console.log("RESPONSE ", response);
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const result = await response.text();
      console.log("RESUlT  ", result);
      return result;
    } catch (error) {
      console.error(
        "There has been a problem with your fetch operation:",
        error
      );
      return "Error fetching the result.";
    }
  };

  const handleCalculate = async () => {
    if (/^\d+$/.test(number)) {
      const n = bigInt(number);
      if (n.greaterOrEquals(0)) {
        const result = await calculateLabseq(n);
        setResult(result);
      } else {
        setResult("Invalid input. Please enter a non-negative integer.");
      }
    } else {
      setResult("Invalid input. Please enter a non-negative integer.");
    }
  };

  return (
    <div>
      <div className="card-container">
        <div className="card">
          <div className="card-content">
            <h2 className="card-title">Calculate the Labseq Number</h2>
            <p className="card-description">
              The labseq, l(n), sequence is defined as follows:
            </p>
            <p className="card-description">n=0 ==&gt; l(0) = 0</p>
            <p className="card-description">n=1 ==&gt; l(1) = 1</p>
            <p className="card-description">n=2 ==&gt; l(2) = 0</p>
            <p className="card-description">n=3 ==&gt; l(3) = 1</p>
            <p className="card-description">
              n=&gt;3 ==&gt; l(n) = l(n-4) + l(n-3)
            </p>
            <input
              className="inputLabseq"
              type="text"
              placeholder=""
              value={number}
              onChange={(e) => {
                setNumber(e.target.value);
                setResult(null);
              }}
            />
            <button className="card-button" onClick={handleCalculate}>
              Calculate
            </button>
          </div>
        </div>
        {result !== null && (
          <div className="card">
            <div className="card-content">
              <h2 className="card-title">Result</h2>
              <p className="card-description">
                The result of the labseq sequence for the number {number}, l(
                {number}), is:
              </p>
              <p className="answer">{result}</p>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
