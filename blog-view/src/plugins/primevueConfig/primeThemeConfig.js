
import { definePreset } from "@primevue/themes"
import Aura from "@primevue/themes/aura"


export const MyPreset = definePreset(Aura, {
    semantic: {
        colorScheme: {
            light: {
                primary: {
                    50: '#f5f3ff',
                    100: '#ede9fe',
                    200: '#ddd6fe',
                    300: '#c4b5fd',
                    400: '#a78bfa',
                    500: '#8b5cf6',
                    600: '#7c3aed',
                    700: '#6d28d9',
                    800: '#5b21b6',
                    900: '#4c1d95',
                    950: '#2e1065'
                },
                button: {
                    background: "{primary.color}",
                    hoverBackground: "{primary.600}",
                    activeBackground: "{primary.700}",
                    borderRadius: "6px",
                    paddingX: "1rem",
                    paddingY: "0.5rem",
                    fontWeight: "500"
                },
                inputtext: {
                    borderRadius: "6px",
                    borderWidth: "1px",
                    focusRing: {
                        width: "2px",
                        style: "solid",
                        color: "{primary.color}",
                        offset: "-1px"
                    }
                },
                card: {
                    borderRadius: "12px",
                    padding: "1.5rem",
                    background: "{surface.50}",
                    borderColor: "{surface.200}"
                },
                accordion: {
                    borderRadius: "8px",
                    borderColor: "{surface.200}",
                    focusRing: {
                        width: "2px",
                        style: "solid",
                        color: "{primary.color}"
                    }
                }
            },
            dark: {
                primary: {
                    50: '#faf5ff',
                    100: '#f3e8ff',
                    200: '#e9d5ff',
                    300: '#d8b4fe',
                    400: '#c084fc',
                    500: '#a855f7',
                    600: '#9333ea',
                    700: '#7e22ce',
                    800: '#6b21a8',
                    900: '#581c87',
                    950: '#3b0764'
                },
                button: {
                    background: "{primary.color}",
                    hoverBackground: "{primary.600}",
                    activeBackground: "{primary.700}"
                },
                card: {
                    background: "{surface.800}",
                    borderColor: "{surface.700}"
                }
            }
        }
    },
    components: {
        button: {
            root: {
                borderRadius: "{semantic.borderRadius.button}",
                paddingX: "{semantic.padding.button.x}",
                paddingY: "{semantic.padding.button.y}",
                fontWeight: "{semantic.font.weight.button}",
                transitionDuration: "0.2s"
            },
            outlined: {
                borderWidth: "1px"
            },
            text: {
                hoverBackground: "transparent"
            }
        },
        inputtext: {
            root: {
                borderRadius: "{semantic.borderRadius.inputtext}",
                borderWidth: "{semantic.border.width.inputtext}"
            }
        },
        card: {
            root: {
                borderRadius: "{semantic.borderRadius.card}",
                padding: "{semantic.padding.card}"
            }
        },
        accordion: {
            root: {
                borderRadius: "{semantic.borderRadius.accordion}"
            },
            tab: {
                root: {
                    borderRadius: "{semantic.borderRadius.accordion}"
                }
            }
        }
    }
})

// Hoặc theme đơn giản hơn
export const SimplePreset = definePreset(Aura, {
    semantic: {
        colorScheme: {
            light: {
                primary: {
                    color: '#3B82F6',
                    50: '#EFF6FF',
                    100: '#DBEAFE',
                    200: '#BFDBFE',
                    300: '#93C5FD',
                    400: '#60A5FA',
                    500: '#3B82F6',
                    600: '#2563EB',
                    700: '#1D4ED8',
                    800: '#1E40AF',
                    900: '#1E3A8A',
                    950: '#172554'
                }
            },
            dark: {
                primary: {
                    color: '#60A5FA'
                }
            }
        }
    },
    options: {
        darkModeSelector: 'media' // Auto dark mode theo hệ thống
    }
})

// Hoặc theme với màu custom
export const CustomPreset = definePreset(Aura, {
    semantic: {
        primary: {
            50: '#f0f9ff',
            100: '#e0f2fe',
            200: '#bae6fd',
            300: '#7dd3fc',
            400: '#38bdf8',
            500: '#0ea5e9',
            600: '#0284c7',
            700: '#0369a1',
            800: '#075985',
            900: '#0c4a6e',
            950: '#082f49'
        },
        colorScheme: {
            light: {
                primary: {
                    color: '{primary.500}',
                    contrastColor: '#ffffff',
                    hoverColor: '{primary.600}'
                },
                surface: {
                    0: '#ffffff',
                    50: '#f8fafc',
                    100: '#f1f5f9',
                    200: '#e2e8f0',
                    300: '#cbd5e1',
                    400: '#94a3b8',
                    500: '#64748b',
                    600: '#475569',
                    700: '#334155',
                    800: '#1e293b',
                    900: '#0f172a',
                    950: '#020617'
                }
            },
            dark: {
                primary: {
                    color: '{primary.400}',
                    contrastColor: '#ffffff',
                    hoverColor: '{primary.300}'
                },
                surface: {
                    0: '#0f172a',
                    50: '#1e293b',
                    100: '#334155',
                    200: '#475569',
                    300: '#64748b',
                    400: '#94a3b8',
                    500: '#cbd5e1',
                    600: '#e2e8f0',
                    700: '#f1f5f9',
                    800: '#f8fafc',
                    900: '#ffffff',
                    950: '#ffffff'
                }
            }
        }
    }
})