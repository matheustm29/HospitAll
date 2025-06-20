PGDMP  .    +                }            hospital    17.5    17.5     -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            0           1262    16450    hospital    DATABASE     n   CREATE DATABASE hospital WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'pt-BR';
    DROP DATABASE hospital;
                     postgres    false                        2615    16451    hospital    SCHEMA        CREATE SCHEMA hospital;
    DROP SCHEMA hospital;
                     postgres    false            �            1259    16480    doenca    TABLE     �   CREATE TABLE hospital.doenca (
    id integer NOT NULL,
    tipo character varying(50) NOT NULL,
    sintomas text,
    restricoes text
);
    DROP TABLE hospital.doenca;
       hospital         heap r       postgres    false    6            �            1259    16479    doenca_id_seq    SEQUENCE     �   CREATE SEQUENCE hospital.doenca_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE hospital.doenca_id_seq;
       hospital               postgres    false    6    221            1           0    0    doenca_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE hospital.doenca_id_seq OWNED BY hospital.doenca.id;
          hospital               postgres    false    220            �            1259    16462    pessoa    TABLE     �  CREATE TABLE hospital.pessoa (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    cpf integer NOT NULL,
    idade integer NOT NULL,
    tipo_pessoa character varying(20) NOT NULL,
    doenca_id integer,
    espec character varying(100),
    instituicao_ensino character varying(150),
    periodo integer,
    crm integer,
    salario numeric(10,2),
    curso character varying(255)
);
    DROP TABLE hospital.pessoa;
       hospital         heap r       postgres    false    6            �            1259    16461    pessoa_id_seq    SEQUENCE     �   CREATE SEQUENCE hospital.pessoa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE hospital.pessoa_id_seq;
       hospital               postgres    false    6    219            2           0    0    pessoa_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE hospital.pessoa_id_seq OWNED BY hospital.pessoa.id;
          hospital               postgres    false    218            �           2604    16483 	   doenca id    DEFAULT     j   ALTER TABLE ONLY hospital.doenca ALTER COLUMN id SET DEFAULT nextval('hospital.doenca_id_seq'::regclass);
 :   ALTER TABLE hospital.doenca ALTER COLUMN id DROP DEFAULT;
       hospital               postgres    false    220    221    221            �           2604    16465 	   pessoa id    DEFAULT     j   ALTER TABLE ONLY hospital.pessoa ALTER COLUMN id SET DEFAULT nextval('hospital.pessoa_id_seq'::regclass);
 :   ALTER TABLE hospital.pessoa ALTER COLUMN id DROP DEFAULT;
       hospital               postgres    false    218    219    219            *          0    16480    doenca 
   TABLE DATA           B   COPY hospital.doenca (id, tipo, sintomas, restricoes) FROM stdin;
    hospital               postgres    false    221   �       (          0    16462    pessoa 
   TABLE DATA           �   COPY hospital.pessoa (id, nome, cpf, idade, tipo_pessoa, doenca_id, espec, instituicao_ensino, periodo, crm, salario, curso) FROM stdin;
    hospital               postgres    false    219   /       3           0    0    doenca_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('hospital.doenca_id_seq', 13, true);
          hospital               postgres    false    220            4           0    0    pessoa_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('hospital.pessoa_id_seq', 60, true);
          hospital               postgres    false    218            �           2606    16487    doenca doenca_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY hospital.doenca
    ADD CONSTRAINT doenca_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY hospital.doenca DROP CONSTRAINT doenca_pkey;
       hospital                 postgres    false    221            �           2606    16471    pessoa pessoa_cpf_key 
   CONSTRAINT     Q   ALTER TABLE ONLY hospital.pessoa
    ADD CONSTRAINT pessoa_cpf_key UNIQUE (cpf);
 A   ALTER TABLE ONLY hospital.pessoa DROP CONSTRAINT pessoa_cpf_key;
       hospital                 postgres    false    219            �           2606    16489    pessoa pessoa_crm_key 
   CONSTRAINT     Q   ALTER TABLE ONLY hospital.pessoa
    ADD CONSTRAINT pessoa_crm_key UNIQUE (crm);
 A   ALTER TABLE ONLY hospital.pessoa DROP CONSTRAINT pessoa_crm_key;
       hospital                 postgres    false    219            �           2606    16469    pessoa pessoa_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY hospital.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY hospital.pessoa DROP CONSTRAINT pessoa_pkey;
       hospital                 postgres    false    219            *   p  x���MN�0���)|��Y
R%@����L�i:��l��A,V!�Ib��ϼ���s�!�q���L�ʠ�P`�
b�57�3�&�!B�ھ�<�����3�j��@}mS`#���c���<�+(�&���NǢ ���[�Y	��+Ҋtc�j��-y1q�
j?"iI7�'���Tj�5�d�y��B����
-n����S�)B��f�f�BCz�NW.��y�K0DLrO�(�lپ�-b��t�[��g[�[pzAm�Wh��U'�,�i$�jHؙCL����s���#��bV4��:�����\>\�g��kܰ�C�n*�翫L2?�M/���\q�Q懿M�?�'��^.�}      (   �  x�m��n�0�ח��	*�o/)a�TMM�]7n�2��=2���$4�d$$��s|�P��|{�@��2ƀj8�ծ~{�����?+��*v�ιBJ	\̜X�$T6������I�q���Zc@ҙ�+���޵6������춀-NV+���ljl��&o;U�3�z%T��r	85L0�4���@6ޝ#��\尯7��0~q�cJ>�;@�0��y*��4�W��o��*m%�^�[�&z|��h5��C�C��1 F�T��--�ck;����>�ػ���34H��|������dAi�~V�����(lfFep]�:�x^���()&Q�㪹��b� �P�������;���G���}���6^0I����/6M�Œ\,Ɵ	
�K~� !�@�����y�}�v��OCpxp�@����W�8~T�7�^b��t�h �f��.��Pp���eJZ��칿t���OY��/��     