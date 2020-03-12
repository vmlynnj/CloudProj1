package util;

public enum EnglishAlphabet {

	a("a"),
	b("b"), 
	c("c"),
	d("d"),
	e("e"),
	f("f"),
	g("g"),
	h("h"),
	i("i"),
	j("j"),
	k("k"),
	l("l"),
	m("m"),
	n("n"),
	o("o"),
	p("p"),
	q("q"),
	r("r"),
	s("s"),
	t("t"),
	u("u"),
	v("v"),
	w("w"),
	x("x"),
	y("y"),
	z("z");
	
    private String letter;

    EnglishAlphabet(String label) {
        this.letter = label;
    }

    public String toString() {
        return letter;
    }
}
