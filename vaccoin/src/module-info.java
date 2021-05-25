module no.site.vaccoin {
	requires gson;
	requires java.sql;
	requires bcprov.jdk15on;
	exports no.site.vaccoin.main;
	opens no.site.vaccoin.main;
}