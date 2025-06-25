INSERT INTO products (title, handle, vendor, product_type, price, variants, created_at, updated_at) VALUES
(
    'Premium Wireless Headphones',
    'premium-wireless-headphones',
    'AudioTech',
    'Electronics',
    299.99,
    '[
        {
            "id": 1001,
            "title": "Black",
            "price": "299.99",
            "sku": "AWH-001-BLK",
            "inventoryQuantity": 15
        },
        {
            "id": 1002,
            "title": "White",
            "price": "299.99",
            "sku": "AWH-001-WHT",
            "inventoryQuantity": 12
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'Organic Cotton T-Shirt',
    'organic-cotton-tshirt',
    'EcoWear',
    'Apparel',
    29.99,
    '[
        {
            "id": 2001,
            "title": "Small / Navy",
            "price": "29.99",
            "sku": "ECO-TEE-S-NVY",
            "inventoryQuantity": 25
        },
        {
            "id": 2002,
            "title": "Medium / Navy",
            "price": "29.99",
            "sku": "ECO-TEE-M-NVY",
            "inventoryQuantity": 30
        },
        {
            "id": 2003,
            "title": "Large / Navy",
            "price": "29.99",
            "sku": "ECO-TEE-L-NVY",
            "inventoryQuantity": 20
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'Smart Fitness Watch',
    'smart-fitness-watch',
    'TechFit',
    'Electronics',
    199.99,
    '[
        {
            "id": 3001,
            "title": "44mm Silver",
            "price": "199.99",
            "sku": "TF-WATCH-44-SLV",
            "inventoryQuantity": 8
        },
        {
            "id": 3002,
            "title": "40mm Rose Gold",
            "price": "189.99",
            "sku": "TF-WATCH-40-RG",
            "inventoryQuantity": 5
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'Artisan Coffee Beans',
    'artisan-coffee-beans',
    'Mountain Roasters',
    'Food & Beverage',
    24.99,
    '[
        {
            "id": 4001,
            "title": "1lb Bag - Medium Roast",
            "price": "24.99",
            "sku": "MR-BEAN-1LB-MED",
            "inventoryQuantity": 45
        },
        {
            "id": 4002,
            "title": "1lb Bag - Dark Roast",
            "price": "24.99",
            "sku": "MR-BEAN-1LB-DRK",
            "inventoryQuantity": 38
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'Sustainable Yoga Mat',
    'sustainable-yoga-mat',
    'ZenLife',
    'Fitness',
    79.99,
    '[
        {
            "id": 5001,
            "title": "6mm Purple",
            "price": "79.99",
            "sku": "ZL-MAT-6MM-PUR",
            "inventoryQuantity": 18
        },
        {
            "id": 5002,
            "title": "6mm Teal",
            "price": "79.99",
            "sku": "ZL-MAT-6MM-TEL",
            "inventoryQuantity": 22
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'Ceramic Plant Pot Set',
    'ceramic-plant-pot-set',
    'Garden & Home',
    'Home & Garden',
    39.99,
    '[
        {
            "id": 6001,
            "title": "Set of 3 - White",
            "price": "39.99",
            "sku": "GH-POT-SET3-WHT",
            "inventoryQuantity": 32
        },
        {
            "id": 6002,
            "title": "Set of 3 - Terracotta",
            "price": "39.99",
            "sku": "GH-POT-SET3-TER",
            "inventoryQuantity": 28
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'Professional Chef Knife',
    'professional-chef-knife',
    'BladeMaster',
    'Kitchen',
    149.99,
    '[
        {
            "id": 7001,
            "title": "8 inch - Stainless Steel",
            "price": "149.99",
            "sku": "BM-KNIFE-8IN-SS",
            "inventoryQuantity": 12
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'Minimalist Desk Lamp',
    'minimalist-desk-lamp',
    'ModernLight',
    'Home Office',
    89.99,
    '[
        {
            "id": 8001,
            "title": "Black Base",
            "price": "89.99",
            "sku": "ML-LAMP-DESK-BLK",
            "inventoryQuantity": 15
        },
        {
            "id": 8002,
            "title": "White Base",
            "price": "89.99",
            "sku": "ML-LAMP-DESK-WHT",
            "inventoryQuantity": 19
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'Bluetooth Portable Speaker',
    'bluetooth-portable-speaker',
    'SoundWave',
    'Electronics',
    79.99,
    '[
        {
            "id": 9001,
            "title": "Charcoal",
            "price": "79.99",
            "sku": "SW-SPEAK-BT-CHR",
            "inventoryQuantity": 23
        },
        {
            "id": 9002,
            "title": "Ocean Blue",
            "price": "79.99",
            "sku": "SW-SPEAK-BT-BLU",
            "inventoryQuantity": 17
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    'Handcrafted Leather Wallet',
    'handcrafted-leather-wallet',
    'Artisan Leather Co.',
    'Accessories',
    69.99,
    '[
        {
            "id": 10001,
            "title": "Brown Leather",
            "price": "69.99",
            "sku": "ALC-WALLET-BRN",
            "inventoryQuantity": 14
        },
        {
            "id": 10002,
            "title": "Black Leather",
            "price": "69.99",
            "sku": "ALC-WALLET-BLK",
            "inventoryQuantity": 11
        }
    ]'::jsonb,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

