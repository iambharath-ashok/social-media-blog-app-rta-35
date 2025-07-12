
# 🧠 Chatflow PaaS - Orchestrator & Router Deep Dive

---

## 🧠 How Orchestrator Makes Decisions and Coordinates Flow

### 🎯 Role of Orchestrator

The **Chat Orchestration Engine** is like a smart traffic controller. Its job is to:
- Understand what the user wants.
- Decide which agent or skill to call.
- Maintain chat context (memory).
- Handle multi-step workflows.
- Track success/failure of each step.

---

### 🛠️ Step-by-Step Breakdown with Example

**User Input:**
> “Can you plan a 3-day trip to Paris under $800?”

#### 🔁 Step 1: Receive Input
- Input received from Gateway.
- Payload: `question="Can you plan a 3-day trip to Paris under $800?"`

#### 🔁 Step 2: Identify Agent to Use
- Agent specified as: `travelPlanner`

#### 🔁 Step 3: Load Workflow
```yaml
agent: travelPlanner
tools:
  - name: weatherTool
    type: api
    endpoint: /weather
  - name: budgetEstimator
    type: function
    params: [location, days, budget]
llm_prompt: >
  You are a smart travel agent. Use context and tools to plan trips.
```

#### 🔁 Step 4: Plan the Execution
1. Get weather info
2. Estimate budget
3. Send everything to LLM

#### 🔁 Step 5: Run Steps
- Call: `weatherAPIWorker("Paris", "3 days")`
- Call: `budgetEstimator("Paris", 3, 800)`
- Prompt LLM with context and tools

#### 🔁 Step 6: Return & Log
- Send response to frontend
- Log telemetry and results

---

## 🧭 How Router Picks the Right Agent

### 🎯 Role of Agent Router

The **Agent Router** is like a matchmaker. It finds the best agent to handle a user request.

---

### 📚 Example 1: Explicit Agent Provided
```json
{ "agent": "travelPlanner" }
```
- Looks up `travelPlanner.yaml` in Agent Registry

---

### 🧠 Example 2: Implicit Agent Routing
**User Input:** “Summarize the latest quarterly sales report.”

#### Router Decision Strategy:
- Keyword match: “summarize” → `reportSummarizer`
- Vector search for intent
- Use tenant default if unsure

---

### 🛠️ Example 3: Conditional Routing
**User Input:** “Help me with deployment issues in Kubernetes”

#### Router matches:
- Intent: devops-help
- Agent chosen: `devOpsHelper.yaml`

---

## ✅ Real-Time Simulation

```plaintext
User: “What’s the cheapest way to travel to Tokyo in winter?”

Frontend → Gateway → Orchestrator
Orchestrator → Router: find best agent
Router → AgentRegistry → `travelPlanner.yaml`
Orchestrator runs:
  - weatherAPI("Tokyo", "Dec-Feb")
  - budgetEstimator("Tokyo", "budget": low)
  - LLM(Prompt + context)
→ Final output is streamed to frontend
```

---

## 🎁 Summary

| Component     | Job |
|---------------|-----|
| **Orchestrator** | Executes step-by-step plan using tools + LLM |
| **Router** | Decides *which agent* to use based on name, intent, or keyword |
| **Agent Config** | YAML/JSON defines what the agent can do and how |
| **Function Workers** | APIs and utilities like weather, cost, GitHub |
| **LLM Worker** | GPT/Claude that takes context + prompt and replies |

---