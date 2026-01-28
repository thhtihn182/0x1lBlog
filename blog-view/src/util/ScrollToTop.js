export function useScrollToTop(){
    const scrollToTop = () => {
        const cubic = value => Math.pow(value,3);
        const easeInOuCubic = value =>
            value < 0.5 ? cubic(value*2) / 2 : 1 - cubic((1-value) * 2) /2;

        const el = document.documentElement;
        const beginTime = Date.now();
        const beginValue = el.scrollTop;
        const duration = 500;

        const rAF = window.requestAnimationFrame ||
            (func => setTimeout(func,16));

        const frameFunc = () => {
            const progress = (Date.now() - beginTime) / duration;

            if(progress < 1){
                el.scrollTop = beginValue * (1 - easeInOuCubic(progress));
                rAF(frameFunc);
            }else
                el.scrollTop = 0;
        };
        rAF(frameFunc);
    }
    return {
        scrollToTop
    };

}