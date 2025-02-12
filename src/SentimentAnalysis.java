import React, { useState } from "react";
import axios from "axios";

const SentimentAnalysis = () => {
  const [text, setText] = useState("");
  const [model, setModel] = useState("Custom Model");
  const [result, setResult] = useState(null);

  const analyzeSentiment = async () => {
    if (!text.trim()) {
      alert("Please enter text to analyze.");
      return;
    }

    try {
      const response = await axios.post("https://af45-35-240-179-226.ngrok-free.app", {  
        text,
        model,
      });

      setResult(response.data);
    } catch (error) {
      console.error("Error:", error);
      alert("Failed to analyze sentiment.");
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 p-4 border rounded-lg shadow-lg bg-white">
      <h2 className="text-xl font-bold mb-4">Sentiment Analysis</h2>

      {/* Text Input Field */}
      <textarea
        className="w-full p-2 border rounded"
        placeholder="Enter your text here..."
        value={text}
        onChange={(e) => setText(e.target.value)}
      />

      {/* Dropdown for Model Selection */}
      <select
        className="w-full p-2 border mt-2 rounded"
        value={model}
        onChange={(e) => setModel(e.target.value)}
      >
        <option value="Custom Model">Custom Model</option>
        <option value="Llama 3">Llama 3</option>
      </select>

      {/* Analyze Button */}
      <button
        className="w-full bg-blue-500 text-white p-2 mt-3 rounded"
        onClick={analyzeSentiment}
      >
        Analyze Sentiment
      </button>

      {/* Result Display Section */}
      {result && (
        <div className="mt-4 p-3 border rounded bg-gray-100">
          <p>
            <strong>Sentiment:</strong> {result.sentiment}
          </p>
          <p>
            <strong>Confidence Score:</strong> {result.confidence.toFixed(2)}
          </p>
          <p>
            <strong>Model Used:</strong> {result.model}
          </p>
        </div>
      )}
    </div>
  );
};

export default SentimentAnalysis;
