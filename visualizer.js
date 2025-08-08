function renderBars(array, highlight1, highlight2) {
    const container = document.getElementById("bars");
    container.innerHTML = "";

    array.forEach((val, index) => {
        const bar = document.createElement("div");
        bar.classList.add("bar");
        bar.style.height = val * 5 + "px";

        if (index === highlight1 || index === highlight2) {
            bar.classList.add("highlight");
        }

        container.appendChild(bar);
    });
}

let i = 0;

function animateSteps(steps) {
    if (i >= steps.length) return;

    const step = steps[i];
    renderBars(step.array, step.index1, step.index2);
    i++;
    setTimeout(() => animateSteps(steps), 400);
}

// steps variable should be passed from JSP
if (typeof steps !== "undefined") {
    animateSteps(steps);
}
