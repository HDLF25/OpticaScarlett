PGDMP          
        
    |           OpticaScarlettBkp    12.20    16.4     -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            0           1262    16393    OpticaScarlettBkp    DATABASE     �   CREATE DATABASE "OpticaScarlettBkp" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Mexico.1252';
 #   DROP DATABASE "OpticaScarlettBkp";
                postgres    false            �            1259    16394 	   articulos    TABLE     �  CREATE TABLE public.articulos (
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
    DROP TABLE public.articulos;
       public         heap    postgres    false            *          0    16394 	   articulos 
   TABLE DATA           �   COPY public.articulos (id_articulo, descripcion_articulo, categoria_articulo, costoactual_articulo, costoanterior_articulo, precioventa_articulo, id_marca, color, material) FROM stdin;
    public          postgres    false    202   �	       �
           2606    16467    articulos articulos_pk 
   CONSTRAINT     ]   ALTER TABLE ONLY public.articulos
    ADD CONSTRAINT articulos_pk PRIMARY KEY (id_articulo);
 @   ALTER TABLE ONLY public.articulos DROP CONSTRAINT articulos_pk;
       public            postgres    false    202            �
           2606    16509    articulos marca_articulos_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.articulos
    ADD CONSTRAINT marca_articulos_fk FOREIGN KEY (id_marca) REFERENCES public.marca(id_marca);
 F   ALTER TABLE ONLY public.articulos DROP CONSTRAINT marca_articulos_fk;
       public          postgres    false    202            *   �  x��V[r�0���� @���1I<����O�?
�-F:I��t-�X�i'���#���#oy?Q��칈r��~�^��^�x�����)O"���S��#��R���L�e�gM�pPO����e�fyy�O�8<9�4�rM�^�)�Y� ���+n�T���CR�V3	٘�9��(�i ����dY��&OL�����w�x6A���yMX^O��i�˘<N9����%�Q��Aq�<���E��2���(���ϖ½��B��od��WdC�C!6n� ��lbYEcV`�w��`!p$ؐ�=����Ws�6 ���9v�+��iw��k�o?�����{1X&
��הV4�R՝ '��v����d<�����RR����k��^tЄ5����߶����4���`��sa �/�>�	�<S���T5d��m f\kSI�$��Us_�1b��Y�5�BinY�Ύ�"˹'j<���J-���D�8E5TRg+v�8�^��Tx�K)����Q_��3�d��#}�&՞U�Wd��򉻏>W����Mӱ�!u��`(4j�%��n��S���d�NB�>�Xw����8nHB_KNT���Zͦ�|	��i��iRd	iϙ��n#wo���%�mܽغ�`I�+I6x��g-R��$�c>�	�7axS���}�E*,17�2	9J��-��F[����Z\֥&�
�c�i��e�c     