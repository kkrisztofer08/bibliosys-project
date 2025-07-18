PGDMP      ,                }        	   librarydb    16.9    17.5     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    24576 	   librarydb    DATABASE     �   CREATE DATABASE librarydb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Hungarian_Hungary.1250';
    DROP DATABASE librarydb;
                     postgres    false            �           0    0    DATABASE librarydb    ACL     ,   GRANT ALL ON DATABASE librarydb TO library;
                        postgres    false    4807            �           0    0    SCHEMA public    ACL     '   GRANT ALL ON SCHEMA public TO library;
                        pg_database_owner    false    5            �            1259    24588    app_user    TABLE     �   CREATE TABLE public.app_user (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255)
);
    DROP TABLE public.app_user;
       public         heap r       library    false            �            1259    24587    app_user_id_seq    SEQUENCE     x   CREATE SEQUENCE public.app_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.app_user_id_seq;
       public               library    false    218            �           0    0    app_user_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.app_user_id_seq OWNED BY public.app_user.id;
          public               library    false    217            �            1259    24579    book    TABLE     �   CREATE TABLE public.book (
    id bigint NOT NULL,
    isbn character varying(255),
    publication_year integer,
    title character varying(255),
    author character varying(255),
    borrowed boolean
);
    DROP TABLE public.book;
       public         heap r       library    false            �            1259    24578    book_id_seq    SEQUENCE     t   CREATE SEQUENCE public.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.book_id_seq;
       public               library    false    216            �           0    0    book_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;
          public               library    false    215            �            1259    24609    borrower    TABLE       CREATE TABLE public.borrower (
    id bigint NOT NULL,
    book_author character varying(255),
    book_title character varying(255),
    email character varying(255),
    end_date date,
    name character varying(255),
    phone character varying(255),
    start_date date
);
    DROP TABLE public.borrower;
       public         heap r       library    false            �            1259    24608    borrower_id_seq    SEQUENCE     x   CREATE SEQUENCE public.borrower_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.borrower_id_seq;
       public               library    false    220            �           0    0    borrower_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.borrower_id_seq OWNED BY public.borrower.id;
          public               library    false    219            %           2604    24591    app_user id    DEFAULT     j   ALTER TABLE ONLY public.app_user ALTER COLUMN id SET DEFAULT nextval('public.app_user_id_seq'::regclass);
 :   ALTER TABLE public.app_user ALTER COLUMN id DROP DEFAULT;
       public               library    false    218    217    218            $           2604    24582    book id    DEFAULT     b   ALTER TABLE ONLY public.book ALTER COLUMN id SET DEFAULT nextval('public.book_id_seq'::regclass);
 6   ALTER TABLE public.book ALTER COLUMN id DROP DEFAULT;
       public               library    false    216    215    216            &           2604    24612    borrower id    DEFAULT     j   ALTER TABLE ONLY public.borrower ALTER COLUMN id SET DEFAULT nextval('public.borrower_id_seq'::regclass);
 :   ALTER TABLE public.borrower ALTER COLUMN id DROP DEFAULT;
       public               library    false    219    220    220            �          0    24588    app_user 
   TABLE DATA           :   COPY public.app_user (id, password, username) FROM stdin;
    public               library    false    218   �       �          0    24579    book 
   TABLE DATA           S   COPY public.book (id, isbn, publication_year, title, author, borrowed) FROM stdin;
    public               library    false    216   6       �          0    24609    borrower 
   TABLE DATA           i   COPY public.borrower (id, book_author, book_title, email, end_date, name, phone, start_date) FROM stdin;
    public               library    false    220   �       �           0    0    app_user_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.app_user_id_seq', 2, true);
          public               library    false    217            �           0    0    book_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.book_id_seq', 3, true);
          public               library    false    215            �           0    0    borrower_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.borrower_id_seq', 7, true);
          public               library    false    219            *           2606    24595    app_user app_user_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.app_user DROP CONSTRAINT app_user_pkey;
       public                 library    false    218            (           2606    24586    book book_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public                 library    false    216            ,           2606    24616    borrower borrower_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.borrower
    ADD CONSTRAINT borrower_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.borrower DROP CONSTRAINT borrower_pkey;
       public                 library    false    220            �   �   x�=�I�0 �5=k
]|a� �Jb�L�ĔJH�4�w�;�s�	ӱ� �)�,�2�/�2XZ;��ym��J�=���)�[�icjp�46#a���k�G>G����߂e#�!�㋛�� ���\�t0�F��Q��.oՓ�E�f�/V      �   �   x�-���0 �s;�wC[(r4�Y�x�K���x�mz��1!:����2�2���	��l���`�S��c�}7Z(q�yQ��B
�q�۔ �	j��|�28k��U�R㳁��*�_�,����+�f��Ӎr�5;R��s��`��;����R�O�8�      �      x������ � �     