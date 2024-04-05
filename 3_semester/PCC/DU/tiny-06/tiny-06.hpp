#pragma once

#include <memory>

enum class generator_kind {
    random,
    mersenne,
    minstd
};


class numbers_generator {
public:
    /**
     * Vygeneruje náhodné číslo v intervalu [0, 1)
     */
    virtual double random_double() = 0;
    /**
     * Vygeneruje náhodné celé číslo v intervalu [lower, upper]
     */
    virtual int    random_int(int lower, int upper) = 0;
    /**
     * Vygeneruje náhodnou pravdivostní hodnotu (true/false)
     */
    virtual bool   random_bool() = 0;

    virtual ~numbers_generator() = default;

    static std::unique_ptr<numbers_generator> create_by_kind(generator_kind kind, size_t seed);

    double min_generated_double() const {return 0;}
    double max_generated_double() const {return 0;}
    size_t doubles_generated() const {return 0;}
    int min_generated_int() const {return 0;}
    int max_generated_int() const {return 0;}
    size_t ints_generated() const {return 0;}
    size_t trues_generated() const {return 0;}
    size_t falses_generated() const {return 0;}
    size_t bools_generated() const {return 0;}
};
