--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: articulos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.articulos (
    id_articulo character varying NOT NULL,
    descripcion_articulo character varying NOT NULL,
    categoria_articulo character varying NOT NULL,
    costoactual_articulo integer NOT NULL,
    costoanterior_articulo integer NOT NULL,
    precioventa_articulo integer NOT NULL,
    id_marca integer NOT NULL,
    color character varying NOT NULL,
    material character varying NOT NULL
);


ALTER TABLE public.articulos OWNER TO postgres;

--
-- Name: ciudad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ciudad (
    id_ciudad integer NOT NULL,
    ciudad character varying NOT NULL
);


ALTER TABLE public.ciudad OWNER TO postgres;

--
-- Name: ciudad_id_ciudad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ciudad_id_ciudad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ciudad_id_ciudad_seq OWNER TO postgres;

--
-- Name: ciudad_id_ciudad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ciudad_id_ciudad_seq OWNED BY public.ciudad.id_ciudad;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    nombre_cliente character varying NOT NULL,
    apellido_cliente character varying NOT NULL,
    menordeedad boolean NOT NULL,
    ci_cliente character varying NOT NULL,
    telefono character varying NOT NULL,
    id_ciudad integer NOT NULL,
    direccion_cliente character varying NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_cliente_seq OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;


--
-- Name: deposito; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.deposito (
    id_deposito integer NOT NULL,
    descripcion_deposito character varying NOT NULL
);


ALTER TABLE public.deposito OWNER TO postgres;

--
-- Name: deposito_id_stock_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.deposito_id_stock_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.deposito_id_stock_seq OWNER TO postgres;

--
-- Name: deposito_id_stock_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.deposito_id_stock_seq OWNED BY public.deposito.id_deposito;


--
-- Name: detalle_ordentrabajo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_ordentrabajo (
    id_ordentrabajo integer NOT NULL,
    id_articulo character varying NOT NULL,
    cantidad_articulo integer NOT NULL,
    precio_articulo integer NOT NULL,
    subtotal_articulo integer NOT NULL
);


ALTER TABLE public.detalle_ordentrabajo OWNER TO postgres;

--
-- Name: marca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marca (
    id_marca integer NOT NULL,
    descripcion_marca character varying NOT NULL
);


ALTER TABLE public.marca OWNER TO postgres;

--
-- Name: marca_id_marca_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.marca_id_marca_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.marca_id_marca_seq OWNER TO postgres;

--
-- Name: marca_id_marca_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.marca_id_marca_seq OWNED BY public.marca.id_marca;


--
-- Name: ordentrabajo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordentrabajo (
    id_ordentrabajo integer NOT NULL,
    id_cliente integer NOT NULL,
    id_paciente integer NOT NULL,
    id_usuario integer NOT NULL,
    ot_estado character varying NOT NULL,
    fecha_ordentrabajo date NOT NULL,
    oi_esferico character varying NOT NULL,
    oi_cilindrico character varying NOT NULL,
    oi_eje character varying NOT NULL,
    oi_adicion character varying NOT NULL,
    oi_cantidad character varying NOT NULL,
    od_esferico character varying NOT NULL,
    od_cilindrico character varying NOT NULL,
    od_eje character varying NOT NULL,
    od_adicion character varying NOT NULL,
    od_cantidad character varying NOT NULL,
    di character varying NOT NULL,
    dnd character varying NOT NULL,
    dni character varying NOT NULL,
    alturafocal character varying NOT NULL,
    id_cristal character varying NOT NULL,
    preciocristal integer NOT NULL,
    observacion character varying NOT NULL,
    subtotal integer NOT NULL,
    sena integer NOT NULL,
    total integer NOT NULL
);


ALTER TABLE public.ordentrabajo OWNER TO postgres;

--
-- Name: ordentrabajo_id_ordentrabajo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ordentrabajo_id_ordentrabajo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ordentrabajo_id_ordentrabajo_seq OWNER TO postgres;

--
-- Name: ordentrabajo_id_ordentrabajo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ordentrabajo_id_ordentrabajo_seq OWNED BY public.ordentrabajo.id_ordentrabajo;


--
-- Name: stock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stock (
    id_articulo character varying NOT NULL,
    id_deposito integer NOT NULL,
    cantidad integer NOT NULL
);


ALTER TABLE public.stock OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    estado character varying NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_usuario_seq OWNER TO postgres;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- Name: ciudad id_ciudad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ciudad ALTER COLUMN id_ciudad SET DEFAULT nextval('public.ciudad_id_ciudad_seq'::regclass);


--
-- Name: cliente id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- Name: deposito id_deposito; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deposito ALTER COLUMN id_deposito SET DEFAULT nextval('public.deposito_id_stock_seq'::regclass);


--
-- Name: marca id_marca; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca ALTER COLUMN id_marca SET DEFAULT nextval('public.marca_id_marca_seq'::regclass);


--
-- Name: ordentrabajo id_ordentrabajo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordentrabajo ALTER COLUMN id_ordentrabajo SET DEFAULT nextval('public.ordentrabajo_id_ordentrabajo_seq'::regclass);


--
-- Name: usuario id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);


--
-- Data for Name: articulos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.articulos (id_articulo, descripcion_articulo, categoria_articulo, costoactual_articulo, costoanterior_articulo, precioventa_articulo, id_marca, color, material) FROM stdin;
Prueba Producto	Producto1	Producto	25000	25000	40000	1	Negro	Plástico
000	sin marca	Producto	0	0	0	1	.	.
BifocalktoAr	Bifocal kto con anti reflejo	Cristal	0	0	300000	1	blanco	organico
Multi Digital	Multifocal Digital	Cristal	850000	0	1300000	1	blanco	organico
32134	Armazon 144	Producto	0	0	100000	1	0	0
ISA22004	Armazon 57-16	Producto	0	0	100000	1	0	0
Propio	Armazon Propio	Producto	0	0	100000	1	0	0
Blue	Monofocal Blue Light cut 	Cristal	0	0	100000	1		
8999	Armazon Betania 53-18	Producto	0	0	150000	1	0	0
1906C1	CUADRADO DAMA CAL 52/15-138	Producto	35000	0	270000	5	NEGRO	TR90
111	ARMAZÓN PROPIO	Producto	0	0	0	1	.	.
FRANCISCA 	ARMAZÓN DE FRANCISCA 	Producto	0	0	0	1	GENERICO	GENERICO
hexa	Hexagonal de plastico	Producto	0	0	100000	1	0	0
Sin registro	sin registro	Producto	0	0	100000	1	0	0
MonoOrganicoBlanco	Monofocal Organico Blanco	Cristal	0	0	100000	1	0	Organico
BifocalBlancoKTO	Bifocal Blanco KTO	Cristal	0	0	150000	1	0	Organico
BifocalBlancoST	Bifocal Blanco ST	Cristal	0	0	180000	1	0	Organico
MultifocalStandar	Multifocal Standar con uvx	Cristal	0	0	280000	1	0	Organico
Multifocal Standar AR	Multifocal Standar AntiReflejo	Cristal	0	0	320000	1	0	Organico
Multifocal Standar Blue	Multifocal Standar Blue	Cristal	0	0	450000	1	0	Organico
Bifocal Kto FotoCromatico	Bifocal Kto Fotocromatico blanco	Cristal	0	0	480000	1	0	Organico
Bifocal Kto Fotocromatico AR	Bifocal Kto Fotocromatico Antireflejo	Cristal	0	0	650000	1	0	Organico
Multifocal Standar fotoblanco	Multifocal Standar Fotocromatico Blanco	Cristal	0	0	500000	1	0	Organico
Multifocal Standar FotoAR	Multifocal Standar Fotocromatico Antireflejo	Cristal	0	0	700000	1	0	Organico
Multifocal Solamax	Multifocal Solamax Blanco	Cristal	0	0	480000	1	0	Organico
Multifocal Solamax Antireflejo	Multifocal Solamax Antireflejo	Cristal	0	0	850000	1	0	Organico
H2033C612	ARMAZON TRANSPARENTE CAL. 51-16/135	Producto	80000	80000	280000	2	TRANSPARENTE	ACETATO
isa19031	Armazon aro completo	Producto	0	0	100000	1	c3	acetato
yy6105	Armazon 53-17	Producto	0	0	100000	1	0	0
6659	Armazon 54-16	Producto	0	0	100000	1	0	0
Monofotoblue	Monofocal fotocromatico bluefilter	Cristal	0	0	225000	1	0	Organico
MonoFotoAr	Monofocal Fotocromatico Antireflejo	Cristal	0	0	150000	1	0	Organico
MonoAntireflejo	Monfocal Antireflejo	Cristal	0	0	75000	1	0	Organico
1302	Armazon Altania 49-16	Producto	0	0	180000	15	0	0
\.


--
-- Data for Name: ciudad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ciudad (id_ciudad, ciudad) FROM stdin;
2	Lambaré
3	Asunción
4	Mariano Roque Alonso
5	REMANSITO
6	San Antonio
7	Ñemby
8	Limpio
9	Capiata
10	J. Augusto Saldivar
11	Ciudad del este
12	Luque
13	San Lorenzo
14	Pedro Juan Caballero
15	Fernando de la mora
16	Encarnacion
17	Coronel Oviedo
18	Caaguazu
19	Itaugua
20	Presidente Franco
21	Hernandarias
22	Minga Guazu
23	Concepcion
24	Villa Elisa
25	Aregua
26	Ita
27	Ypane
28	Villarrica
29	Caacupe
30	Villa Hayes
31	Cambyreta
32	San Estanislao
33	Santa rosa del Aguaray
34	Horqueta
35	Doctor Juan Eulogio Estigarribia
36	Villeta
37	Pilar
38	Curuguaty
39	San Pedro Ycuamandiyu
40	Capiibary
41	San Ignacio Guazu
42	Carapegua
43	Yaguaron
44	Saltos del Guaira
45	San Juan Neponuceno
46	Guayaibi
47	Guarambare
48	Tobati
49	Itakyry
50	Santa Rita
51	Piribebuy
52	Tomás Romero Pereira
53	Repatriación
54	Caazapá
55	Choré
56	Yhú
57	San Pedro del Paraná
58	Benjamín Aceval
59	\tAbaí
60	Emboscada
61	Ypacaraí
62	San Juan Bautista
63	Paraguarí
64	Filadelfia
65	Paso Yobái
66	Loma Plata
67	Doctor Juan Manuel Frutos
68	Yasy Cañy
69	Arroyos y Esteros
70	Teniente Irala
71	Yby Yaú
72	Eusebio Ayala
73	Independencia
74	Liberación
75	Capitán Bado
76	Ybycuí
77	Pirayú
78	Edelira
79	Ayolas
80	Coronel Bogado
81	General Elizardo Aquino
82	Boquerón
83	Tavaí
84	Santa Rosa de Lima
85	Doctor Juan León Mallorquín
86	Juan Emiliano OLeary
87	Atyrá
88	Maracaná
89	Yuty
90	Natalio
91	Quiindy
92	Altos
93	San Rafael del Paraná
94	San José de los Arroyos
95	Tacuatí
96	Carlos Antonio López
97	Obligado
98	Loreto
99	Mariscal Estigarribia
100	Villa Ygatimí
101	Tres de Mayo
102	Tembiaporá
103	Hohenau
104	Acahay
105	Raúl Arsenio Oviedo
106	Yby Pytá
107	Yrybucuá
108	San Bernardino
109	Bella Vista Norte
110	Minga Porá
111	San Joaquín
112	San Vicente Pancholo
113	Alto Verá
114	San Lázaro
115	San Alberto
116	Nueva Esperanza
117	Carayaó
118	Katueté
119	Cerro Corá
120	Belén
121	Itacurubí del Rosario
122	Vaquería
123	Bella Vista Sur
124	Lima
125	Caraguatay
126	Yataity del Norte
127	Mayor Otaño
128	General Isidoro Resquín
129	Nueva Italia
130	San Roque
131	Capitán Mauricio José Troche
132	Capitán Miranda
133	Itapúa Poty
134	Capitán Meza
135	Puerto Pinasco
136	San Juan del Paraná
137	Itacurubí de la Cordillera
138	25 de Diciembre
139	Yatytay
140	General Artigas
141	Villa del Rosario
142	Ypejhú
143	San Cristóbal
144	Ybyrarobaná
145	Santa Rosa del Mbutuy
146	Fram
147	Arroyito
148	Yguazú
149	Mbocayaty del Guairá
150	Trinidad
151	General Álvarez
152	Borja
153	Corpus Christi
154	Horqueta
155	Alberdi
156	Tres de Febrero
157	Santa María de Fe
158	José Domingo Ocampos
159	San Cosme y Damián
160	La Paloma del Espíritu Santo
161	Los Cedrales
162	Iturbe
163	Tavapy
164	Mbuyapey
165	General Eugenio A. Garay
166	Azotey
167	Naranjal
168	Ñacunday
169	Carmen del Paraná
170	Mbaracayú
171	Juan de Mena
172	Sargento José Félix López
173	Pirapó
174	Itapé
175	Caapucú
176	R.I. Tres Corrales
177	Nanawa
178	Santiago
179	Santa Rosa del Monday
180	Valenzuela
181	Campo Aceval
182	General Delgado
183	Nueva Germania
184	Doctor Raúl Peña
185	Puerto Casado
186	Zanja Pytá
187	Ybytymí
188	Mariscal López
189	Isla Pucú
190	La Colmena
191	Sapucai
192	Jesús
193	José Fassardi
194	San Miguel
195	Fulgencio Yegros
196	Domingo Martínez de Irala
197	Unión
198	Primero de Marzo
199	\tKarapaí
200	Nueva Alborada
201	Nueva Toledo
202	Doctor Cecilio Báez
203	Félix Pérez Cardozo
204	General Bernardino Caballero
205	Escobar
206	Simón Bolívar
207	General Higinio Morínigo
208	Nueva Colombia
209	Quyquyhó
210	Fuerte Olimpo
211	Coronel Martínez
212	Capitán Carmelo Peralta
213	Santa Elena
214	Cerrito
215	Buena Vista
216	Antequera
217	Yataity del Guairá
218	San José del Rosario
219	Paso Barreto
220	Santa Fe del Paraná
221	Iruña
222	Natalicio Talavera
223	Loma Grande
224	Doctor Moisés Santiago Bertoni
225	San Juan Bautista de Ñeembucú
226	José Falcón
227	Nueva Londres
228	Maciel
229	San Pablo
230	Villa Oliva
231	Mbocayaty del Yhaguy
232	La Pastora
233	Tebicuarymí
234	San Patricio
235	Nueva Asunción
236	General José Eduvigis Díaz
237	\tVilla Florida
238	Mayor José Martínez
239	San José Obrero
240	María Antonia
241	Ñumí
242	Bahía Negra
243	Teniente Esteban Martínez
244	Tacuaras
245	La Paz
246	\tTebicuary
247	Laurel
248	San Salvador
249	General José María Bruguez
250	José Leandro Oviedo
251	Humaitá
252	San Alfredo
253	Itacuá
254	Laureles
255	Itanará
256	Isla Umbú
257	Yabebyry
258	\tPaso de Patria
259	Villalbín
260	Guazú Cuá
261	Puerto Adela
262	Doctor Bottrell
263	Desmochados
264	Villa Franca
265	San Carlos del Apa
266	Sin registro
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id_cliente, nombre_cliente, apellido_cliente, menordeedad, ci_cliente, telefono, id_ciudad, direccion_cliente) FROM stdin;
1	Hugo David	López Fariña	f	5118644	0981133948	2	11 de Septiembre
3	Emilio Samuel 	Maldonado Peña	f	5456356-9	0971644864	3	cerrocora casi pasaje molas
2	Lariza Micaela 	Figueredo Jara	f	5346958-5	0974133258	4	avenida gervacio leon casi calle corta 
4	CELINA 	FERREIRA	f	3888105	0986431422	4	SIN DIRECCIÓN
5	DIEGO 	PAREDES	f	4883706	0994601023	3	SIN DIRECCIÓN
6	Natalia Teresa	Pereira Bareiro	f	4443905	0992480351	266	sin registro
8	Myriam 	Gonzalez	f	5190728	0987302788	266	sin registro
9	Jazmin	Gonzalez	f	5981415	0976854415	266	0
10	Nadia	Herrera	f	3998618-7	0981839972	266	0
11	Ariel	Camacho	f	5248407	0	266	0
12	Ramona	Garcete	f	618294	0972281009	10	0
13	Loren	Signorino	f	1224014	0982444075	266	0
14	Mateo	Campuzano	t	Menor	0984198043	266	0
15	Gloria Mabel	Paez	f	4649621	0	266	0
\.


--
-- Data for Name: deposito; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.deposito (id_deposito, descripcion_deposito) FROM stdin;
1	Salón
\.


--
-- Data for Name: detalle_ordentrabajo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalle_ordentrabajo (id_ordentrabajo, id_articulo, cantidad_articulo, precio_articulo, subtotal_articulo) FROM stdin;
1	Prueba Producto	1	40000	40000
2	Prueba Producto	1	40000	40000
3	H2033C612	1	140000	140000
4	111	1	0	0
5	1906C1	1	100000	100000
6	FRANCISCA 	1	100000	100000
7	hexa	1	100000	100000
8	isa19031	1	100000	100000
9	32134	1	100000	100000
11	ISA22004	1	100000	100000
10	yy6105	1	100000	100000
12	6659	1	100000	100000
13	111	1	0	0
14	1302	1	180000	180000
15	8999	1	150000	150000
\.


--
-- Data for Name: marca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.marca (id_marca, descripcion_marca) FROM stdin;
1	Sin Marca
2	UNICITY
3	STEFFANI
4	AMOR
5	BETANIA
6	RALC
7	MIRAFLEX
8	D&F - DAFLOR
9	UNICITY
10	ETERNAL GLAMOUR
11	VISTA GLAMOUR
12	USUAL
13	ESOTICO
14	CLASSIC VISTA GLAMOUR
15	ALTANIA
16	KASWINI
17	GIOVANNI
18	BONNER EYEWEAR
19	SILOAM 153 PARIS
20	VANCCI
21	Feillis
\.


--
-- Data for Name: ordentrabajo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ordentrabajo (id_ordentrabajo, id_cliente, id_paciente, id_usuario, ot_estado, fecha_ordentrabajo, oi_esferico, oi_cilindrico, oi_eje, oi_adicion, oi_cantidad, od_esferico, od_cilindrico, od_eje, od_adicion, od_cantidad, di, dnd, dni, alturafocal, id_cristal, preciocristal, observacion, subtotal, sena, total) FROM stdin;
5	2	2	1	Cerrado	2024-02-19	-1.00	0	0	0	1	-1.00	0	0	0	1	60	30	30	0	Blue	180000	0	180000	280000	0
4	4	4	1	Cerrado	2022-12-07	+0.50	-0.50	100°	+2.50	1	+0.50	-0.75	85°	+2.50	1	63	30	33	24	MULTISTANDAR	115000	SIN OBSERVACIÓN 	230000	230000	0
6	2	2	1	Cerrado	2024-09-11	-0.25	0.50	180	0	1	-0.25	0.50	180	0	1	58	29	29	0	Blue	100000	0	200000	280000	20000
3	2	2	1	Cerrado	2022-09-13	-1.25	-0.75	110°	0	1	-1.25	-0.75	80°	0	1	60	30	30	0	Blue	170000	0	340000	480000	0
2	2	2	1	Cerrado	2022-08-25	000	000	00	00	1	000	000	00	00	1	30	30	30	0	Blue	110000	0	220000	260000	0
1	3	3	1	Cerrado	2022-08-24	-0.50	0	0	0	1	-0.50	0	0	0	1	61	30	31	0	blue light	90000	0	180000	220000	0
8	8	8	1	Cerrado	2024-09-12	+0.50	-0.50	90	100	1	+0.25	-0.25	90	100	1	60	30	30	0	BifocalBlancoKTO	85000	ninguna	170000	270000	0
9	9	9	1	Cerrado	2024-09-12	+025	-025	180	0	1	+025	-025	180	0	1	0	0	0	0	Blue	115000	0	230000	330000	0
10	10	10	1	Cerrado	2024-09-12	0	-200	10	0	1	0	-100	165	0	1	0	30	32	0	Blue	125000	0	250000	350000	0
11	11	11	1	Cerrado	2024-09-12	-100	-250	120	0	1	plano	-200	60	0	1	0	0	0	0	MonoFotoAr	150000	0	300000	400000	0
12	12	12	1	Cerrado	2024-09-12	-425	-225	90	250	1	-425	-225	75	250	1	0	28,5	29,5	0	Multifocal Solamax	225000	0	450000	550000	0
7	6	6	1	Cerrado	2024-09-11	0	-050	90	0	1	0	-050	90	0	1	0	0	0	0	Blue	100000	0	200000	300000	0
13	13	13	1	Cerrado	2024-09-12	+150	0	0	0	1	+150	0	0	0	1	60	30	30	0	Blue	105000	0	210000	210000	0
14	14	14	1	Cerrado	2024-09-12	-700	0	0	0	1	-700	0	0	0	1	50	25	25	0	MonoAntireflejo	200000	UltraDelgado indice 1,67	400000	580000	0
15	15	15	1	Cerrado	2024-09-12	-175	-025	25	0	1	-150	0	0	0	1	0	0	0	0	Monofotoblue	225000	0	450000	600000	0
\.


--
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stock (id_articulo, id_deposito, cantidad) FROM stdin;
000	1	100
BifocalktoAr	1	1
Multi Digital	1	10
isa19031	1	0
BifocalBlancoKTO	1	-1
32134	1	0
yy6105	1	0
ISA22004	1	0
6659	1	0
Multifocal Solamax	1	-1
Propio	1	1
111	1	999999999
MonoFotoAr	1	-1
Blue	1	-16
1302	1	0
MonoAntireflejo	1	-1
8999	1	0
Monofotoblue	1	-1
FRANCISCA 	1	999
hexa	1	0
Sin registro	1	1
MonoOrganicoBlanco	1	1
BifocalBlancoST	1	1
MultifocalStandar	1	1
Multifocal Standar AR	1	1
Multifocal Standar Blue	1	1
Bifocal Kto FotoCromatico	1	1
Bifocal Kto Fotocromatico AR	1	1
Multifocal Standar fotoblanco	1	1
Multifocal Standar FotoAR	1	1
Multifocal Solamax Antireflejo	1	1
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id_usuario, username, password, estado) FROM stdin;
1	lariza	130a5da7d3bace9390d82dedf8fac998	A
2	hdavid25	acc3785f4e29dd2872c83f290e76372a	A
\.


--
-- Name: ciudad_id_ciudad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ciudad_id_ciudad_seq', 266, true);


--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 15, true);


--
-- Name: deposito_id_stock_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.deposito_id_stock_seq', 1, false);


--
-- Name: marca_id_marca_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marca_id_marca_seq', 21, true);


--
-- Name: ordentrabajo_id_ordentrabajo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ordentrabajo_id_ordentrabajo_seq', 15, true);


--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 2, true);


--
-- Name: articulos articulos_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articulos
    ADD CONSTRAINT articulos_pk PRIMARY KEY (id_articulo);


--
-- Name: ciudad ciudad_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ciudad
    ADD CONSTRAINT ciudad_pk PRIMARY KEY (id_ciudad);


--
-- Name: cliente cliente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pk PRIMARY KEY (id_cliente);


--
-- Name: deposito deposito_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deposito
    ADD CONSTRAINT deposito_pk PRIMARY KEY (id_deposito);


--
-- Name: detalle_ordentrabajo detalle_ordentrabajo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_ordentrabajo
    ADD CONSTRAINT detalle_ordentrabajo_pk PRIMARY KEY (id_ordentrabajo, id_articulo);


--
-- Name: marca marca_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca
    ADD CONSTRAINT marca_pk PRIMARY KEY (id_marca);


--
-- Name: ordentrabajo ordentrabajo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordentrabajo
    ADD CONSTRAINT ordentrabajo_pk PRIMARY KEY (id_ordentrabajo);


--
-- Name: stock stock_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT stock_pk PRIMARY KEY (id_articulo, id_deposito);


--
-- Name: usuario usuario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY (id_usuario);


--
-- Name: detalle_ordentrabajo articulos_detalle_ordentrabajo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_ordentrabajo
    ADD CONSTRAINT articulos_detalle_ordentrabajo_fk FOREIGN KEY (id_articulo) REFERENCES public.articulos(id_articulo);


--
-- Name: stock articulos_stock_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT articulos_stock_fk FOREIGN KEY (id_articulo) REFERENCES public.articulos(id_articulo);


--
-- Name: cliente ciudad_cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT ciudad_cliente_fk FOREIGN KEY (id_ciudad) REFERENCES public.ciudad(id_ciudad);


--
-- Name: ordentrabajo cliente_ordentrabajo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordentrabajo
    ADD CONSTRAINT cliente_ordentrabajo_fk FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);


--
-- Name: stock deposito_stock_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT deposito_stock_fk FOREIGN KEY (id_deposito) REFERENCES public.deposito(id_deposito);


--
-- Name: articulos marca_articulos_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articulos
    ADD CONSTRAINT marca_articulos_fk FOREIGN KEY (id_marca) REFERENCES public.marca(id_marca);


--
-- Name: detalle_ordentrabajo ordentrabajo_detalle_ordentrabajo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_ordentrabajo
    ADD CONSTRAINT ordentrabajo_detalle_ordentrabajo_fk FOREIGN KEY (id_ordentrabajo) REFERENCES public.ordentrabajo(id_ordentrabajo);


--
-- Name: ordentrabajo usuario_ordentrabajo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordentrabajo
    ADD CONSTRAINT usuario_ordentrabajo_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- PostgreSQL database dump complete
--

