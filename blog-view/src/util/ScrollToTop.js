export function useScrollToTop() {
    const rAF = window.requestAnimationFrame
        || (cb => setTimeout(cb, 16))
    let animId = null

    const scrollToTop = (options = {}) => {
        const {
            duration = 800,
            element = document.documentElement
        } = options

        if (animId) return ;

        const start = performance.now()
        const from = element.scrollTop

        const animate = (now) => {
            const time = Math.min(1, (now - start) / duration)
            const eased = time < 0.5 ? 4 * time * time * time : 1 - Math.pow(-2 * time + 2, 3) / 2

            element.scrollTop = from * (1 - eased)
            if (time < 1) {
                animId = rAF(animate)
            } else {
                animId = null
            }
        }

        animId = rAF(animate)
    }

    const stop = () => {
        if (animId) {
            const cancel = window.cancelAnimationFrame || clearTimeout
            cancel(animId)
            animId = null
        }
    }

    return { scrollToTop, stop }
}