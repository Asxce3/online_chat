--
-- PostgreSQL database dump
--

-- Dumped from database version 14.13 (Homebrew)
-- Dumped by pg_dump version 14.13 (Homebrew)

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
-- Name: message; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.message (
    id integer NOT NULL,
    user_id integer,
    text character varying(255),
    date_message date
);


--
-- Name: message_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.message_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.message_id_seq OWNED BY public.message.id;


--
-- Name: person; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person (
    id integer NOT NULL,
    username character varying(30) NOT NULL,
    password character varying(30) NOT NULL
);


--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;


--
-- Name: person_session; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_session (
    id integer NOT NULL,
    user_id integer,
    session_id character varying
);


--
-- Name: person_session_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.person_session_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: person_session_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.person_session_id_seq OWNED BY public.person_session.id;


--
-- Name: refresh_token; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.refresh_token (
    id integer NOT NULL,
    person_id integer,
    token character varying(255) NOT NULL
);


--
-- Name: refresh_token_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.refresh_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: refresh_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.refresh_token_id_seq OWNED BY public.refresh_token.id;


--
-- Name: room; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.room (
    id integer NOT NULL,
    room_name character varying(15) NOT NULL
);


--
-- Name: room_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: room_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.room_id_seq OWNED BY public.room.id;


--
-- Name: room_message; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.room_message (
    room_id integer,
    message_id integer NOT NULL
);


--
-- Name: room_message_message_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.room_message_message_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: room_message_message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.room_message_message_id_seq OWNED BY public.room_message.message_id;


--
-- Name: room_person; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.room_person (
    room_id integer,
    user_id integer
);


--
-- Name: message id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message_id_seq'::regclass);


--
-- Name: person id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);


--
-- Name: person_session id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_session ALTER COLUMN id SET DEFAULT nextval('public.person_session_id_seq'::regclass);


--
-- Name: refresh_token id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_token ALTER COLUMN id SET DEFAULT nextval('public.refresh_token_id_seq'::regclass);


--
-- Name: room id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.room ALTER COLUMN id SET DEFAULT nextval('public.room_id_seq'::regclass);


--
-- Name: room_message message_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.room_message ALTER COLUMN message_id SET DEFAULT nextval('public.room_message_message_id_seq'::regclass);


--
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.message (id, user_id, text, date_message) FROM stdin;
1	2	какой-то текст	2024-11-14
2	2	какой-то текст	2024-11-14
5	2	какой-то текст	2024-11-14
7	2	какой-то текст	2024-11-14
8	2	какой-то текст	2024-11-14
9	2	какой-то текст	2024-11-14
11	2	какой-то текст	2024-11-14
12	2	какой-то текст	2024-11-14
14	2	длтыфдлвтфлдытвдфыв	2024-11-14
16	2	длтыфдлвтфлдытвдфыв	2024-11-14
17	2	длтыфдлвтфлдытвдфыв	2024-11-14
20	2	цукенгш	2024-11-14
21	2	цукенгш	2024-11-14
22	2	цукенгш	2024-11-14
\.


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person (id, username, password) FROM stdin;
1	Oleg	Olegpass
2	Samvel	SamvelPass1234
7	Leva	LevaPass
8	Vitya	VityaPass
\.


--
-- Data for Name: person_session; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person_session (id, user_id, session_id) FROM stdin;
\.


--
-- Data for Name: refresh_token; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.refresh_token (id, person_id, token) FROM stdin;
79	1	eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJPbGVnIiwiZXhwIjoxNzMyNTM5Nzk0fQ.rbV6UzrP8dnbntyJEBYpnQVKYure0lUkg0B9iIRmZZk
81	2	eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MiwidXNlcm5hbWUiOiJTYW12ZWwiLCJleHAiOjE3MzI4ODM4ODB9.gUi1oB_3bpiGDT2p4eregNMtZcRu06SQiy_Jsq7fdYo
\.


--
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.room (id, room_name) FROM stdin;
1	QWE
\.


--
-- Data for Name: room_message; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.room_message (room_id, message_id) FROM stdin;
1	1
1	2
1	5
1	7
1	8
1	9
1	11
1	12
1	14
1	16
1	17
1	20
1	21
1	22
\.


--
-- Data for Name: room_person; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.room_person (room_id, user_id) FROM stdin;
1	2
\.


--
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.message_id_seq', 22, true);


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.person_id_seq', 8, true);


--
-- Name: person_session_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.person_session_id_seq', 2, true);


--
-- Name: refresh_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.refresh_token_id_seq', 81, true);


--
-- Name: room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.room_id_seq', 1, false);


--
-- Name: room_message_message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.room_message_message_id_seq', 1, false);


--
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: person_session person_session_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_session
    ADD CONSTRAINT person_session_pkey PRIMARY KEY (id);


--
-- Name: person person_username_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_username_key UNIQUE (username);


--
-- Name: refresh_token refresh_token_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_pkey PRIMARY KEY (id);


--
-- Name: refresh_token refresh_token_token_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_token_key UNIQUE (token);


--
-- Name: room room_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- Name: room room_room_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_room_name_key UNIQUE (room_name);


--
-- Name: message message_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.person(id);


--
-- Name: person_session person_session_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_session
    ADD CONSTRAINT person_session_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.person(id);


--
-- Name: refresh_token refresh_token_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- Name: room_message room_message_message_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.room_message
    ADD CONSTRAINT room_message_message_id_fkey FOREIGN KEY (message_id) REFERENCES public.message(id) ON DELETE CASCADE;


--
-- Name: room_message room_message_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.room_message
    ADD CONSTRAINT room_message_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- Name: room_person room_person_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.room_person
    ADD CONSTRAINT room_person_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- Name: room_person room_person_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.room_person
    ADD CONSTRAINT room_person_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.person(id);


--
-- PostgreSQL database dump complete
--

